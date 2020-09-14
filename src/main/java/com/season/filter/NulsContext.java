package com.season.filter;



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
