package com.season.filter;


import io.nuls.core.tools.log.Log;
import io.nuls.kernel.constant.KernelErrorCode;
import io.nuls.kernel.exception.NulsRuntimeException;
import io.nuls.kernel.lite.core.SpringLiteContext;
import io.nuls.kernel.model.Block;

import java.util.List;
 
public class NulsContext {

    public static volatile Integer WALLET_STATUS = 1;

    /**
     * 当前钱包最新协议版本(用于系统升级，默认为1，启动时会根据当前钱包的协议配置做修改)
     * System protocol version.
     */
    public static volatile Integer CURRENT_PROTOCOL_VERSION = 1;

    /**
     * 多重签名地址
     * contract address type
     */
    public static byte P2SH_ADDRESS_TYPE = 3;

    /**
     * 主网运行中的版本，默认为1，会根据钱包更新到的块的最新版本做修改
     */
    public static volatile Integer MAIN_NET_VERSION = 1;
    /**
     * 主网运行中的版本对应的区块起始高度，默认为0，会根据钱包更新到最新版本做修改
     */
    public static volatile Long MAIN_NET_VERSION_HEIGHT = 0L;

    /**
     * 最后一次升级，用于判断是否停止网络
     *
     * @return;
     */
    public static boolean isNetFinished(int delay) {
        long bestHeight = NulsContext.getInstance().getBestHeight();
        if (MAIN_NET_VERSION >= 4 && bestHeight - MAIN_NET_VERSION_HEIGHT >= delay) {
            return true;
        }
        return false;
    }

    /**
     * 切换序列化交易HASH方法的高度
     */
    public static Long CHANGE_HASH_SERIALIZE_HEIGHT;

    /**
     * 默认链id（nuls主链）,链id会影响地址的生成，当前地址以“Ns”开头
     * The default chain id (nuls main chain), the chain id affects the generation of the address,
     * and the current address begins with "Ns".8964.
     */
    public Short defaultChainId;

    /**
     * 默认的地址类型，一条链可以包含几种地址类型，地址类型包含在地址中
     * The default address type, a chain can contain several address types, and the address type is contained in the address.
     */
    public static byte DEFAULT_ADDRESS_TYPE = 1;

    /**
     * 智能合约地址类型
     * contract address type
     */
    public static byte CONTRACT_ADDRESS_TYPE = 2;

    public static String INITIAL_STATE_ROOT = "56e81f171bcc55a6ff8345e692c0f86e5b48e01b996cadc001622fb5e363b421";

    /**
     * cache the best block
     */
    private Block bestBlock;

    /**
     * cache the genesis block
     */
    private Block genesisBlock;

    /**
     * 同一个出块地址连续3轮发出两个相同高度，但不同hash的block，节点将会被红牌惩罚
     */
    public static byte REDPUNISH_BIFURCATION = 3;

    /**
     * 网络最新区块高度
     * Network latest block height.
     */
    private Long netBestBlockHeight = 0L;

    private static String dataPath;

    public static Long totalNuls = 10000000000000000L;

    public static Long lockedNuls = 0L;

    public int getStop() {
        return stop;
    }

    private int stop = 0;

    public void exit(int stop) {
        this.stop = stop;
    }

    /**
     * 是否需要强制升级
     */
    public static volatile boolean mastUpGrade = false;

    /**
     * 获取创世块
     * get the block height is 0
     *
     * @return block
     */
    public Block getGenesisBlock() {
        while (genesisBlock == null) {
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
            }
        }
        return genesisBlock;
    }

    public void setGenesisBlock(Block block) {
        this.genesisBlock = block;
    }

    public Block getBestBlock() {
        if (bestBlock == null) {
            bestBlock = getGenesisBlock();
        }
        return bestBlock;
    }

    /**
     * 获取本地最新高度
     * Get the latest local height.
     *
     * @return long height
     */
    public long getBestHeight() {
        if (bestBlock == null) {
            bestBlock = getGenesisBlock();
        }
        return bestBlock.getHeader().getHeight();
    }

    private NulsContext() {
    }

    private static final NulsContext NC = new NulsContext();

    /**
     * get zhe only instance of NulsContext
     *
     * @return NulsContext
     */
    public static final NulsContext getInstance() {
        return NC;
    }


    public void setBestBlock(Block bestBlock) {
        if (bestBlock == null) {
            throw new RuntimeException("best block set to null!");
        }
        this.bestBlock = bestBlock;
//        Log.info("best height:"+bestBlock.getHeader().getHeight()+", hash:"+bestBlock.getHeader().getHash());
    }

    /**
     * 根据服务类型获取实例
     * Gets an instance based on the service type.
     *
     * @param tClass 服务类型
     * @param <T>    泛型
     * @return 实例对象
     */
    public static final <T> T getServiceBean(Class<T> tClass) {
        try {

            return SpringLiteContext.getBean(tClass);
        } catch (Exception e) {
            return getServiceBean(tClass, 0L);
        }
    }

    private static <T> T getServiceBean(Class<T> tClass, long l) {
        try {
            Thread.sleep(200L);
//            System.out.println("获取service失败！"+tClass);
        } catch (InterruptedException e1) {
            Log.error(e1);
        }
        try {
            return SpringLiteContext.getBean(tClass);
        } catch (NulsRuntimeException e) {
            if (KernelErrorCode.DATA_ERROR.getCode().equals(e.getCode())) {
                throw e;
            }
            if (l > 1200) {
                Log.error(e);
                return null;
            }
            return getServiceBean(tClass, l + 10L);
        }
    }

    /**
     * 获取网络最新高度，当缓存的网络最新高度为空或者小于本地高度时，返回本地最新高度
     * To get the latest height of the network, return to the local latest height
     * when the cached network's latest height is empty or less than the local height.
     *
     * @return long best height
     */
    public Long getNetBestBlockHeight() {
        if (null != bestBlock && netBestBlockHeight < bestBlock.getHeader().getHeight()) {
            return bestBlock.getHeader().getHeight();
        }
        if (null == netBestBlockHeight) {
            return 0L;
        }
        return netBestBlockHeight;
    }

    /**
     * 获取缓存的网络最新高度
     * Gets the latest height of the cached network.
     *
     * @return Long
     */
    public Long getNetBestBlockHeightWithNull() {
        return netBestBlockHeight;
    }

    public void setNetBestBlockHeight(Long netBestBlockHeight) {
        this.netBestBlockHeight = netBestBlockHeight;
    }

    /**
     * 根据服务类型获取该类型所有实例
     * Gets all instances of this type according to the service type.
     *
     * @param tClass 服务类型
     * @param <T>    泛型
     * @return 实例列表
     */
    public static <T> List<T> getServiceBeanList(Class<T> tClass) {
        try {
            return SpringLiteContext.getBeanList(tClass);
        } catch (Exception e) {
            Log.error(e);
        }
        return null;
    }

    public short getDefaultChainId() {
        if (null == defaultChainId) {
            Log.error("Default chain id is null!");
            throw new NulsRuntimeException(KernelErrorCode.DATA_ERROR);
        }
        return defaultChainId;
    }

    public void setDefaultChainId(Short defaultChainId) {
        this.defaultChainId = defaultChainId;
    }

    public static String getDataPath() {
        return dataPath;
    }

    public static void setDataPath(String dataPath) {
        NulsContext.dataPath = dataPath;
    }
}
