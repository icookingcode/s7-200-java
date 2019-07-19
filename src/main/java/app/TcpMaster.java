package app;

import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.ip.IpParameters;

/**
 * @Author guc
 * @Date 2019/7/17 11:36
 * @Description TODO
 */
public class TcpMaster {
    private static ModbusFactory modbusFactory;

    static {
        if (modbusFactory == null) {
            modbusFactory = new ModbusFactory();
        }
    }

    /**
     * 获取master
     *
     * @return master
     */
    public static ModbusMaster getMaster() {
        IpParameters params = new IpParameters();
//        params.setHost("localhost");
        params.setHost("192.168.64.3");
        params.setPort(502);
        params.setEncapsulated(false);
        ModbusMaster master = modbusFactory.createTcpMaster(params, true);// TCP 协议
        try {
            //设置超时时间
            master.setTimeout(1000);
            //设置重连次数
            master.setRetries(3);
            //初始化
            master.init();
        } catch (ModbusInitException e) {
            e.printStackTrace();
        }
        return master;
    }
}
