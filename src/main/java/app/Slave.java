package app;

import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusSlaveSet;
import com.serotonin.modbus4j.exception.ModbusInitException;

/**
 * @Author guc
 * @Date 2019/7/17 11:44
 * @Description TODO
 */
public class Slave {

    public void run(String... args) throws Exception {
        createSalve();
    }

    private void createSalve() {
        //创建modbus工厂
        ModbusFactory modbusFactory = new ModbusFactory();
        //创建TCP服务端
        final ModbusSlaveSet salve = modbusFactory.createTcpSlave(true);
        //向过程影像区添加数据
        salve.addProcessImage(Register.getModscanProcessImage(1));
        //获取寄存器
        salve.getProcessImage(1);
        try {
            salve.start();
            System.out.println("slave has started");
        } catch (ModbusInitException e) {
            e.printStackTrace();
            System.out.println("slave has started failure");
        }
    }

}
