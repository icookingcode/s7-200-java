package app;

import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.code.DataType;

/**
 * @Author guc
 * @Date 2019/7/17 11:34
 * @Description TODO
 */
public class main {
    public static void main(String[] args) {
//        Slave slave = new Slave();
//        try {
//            slave.run();
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
        Modbus4jReadUtils readUtils = new Modbus4jReadUtils();
        Modbus4jWriteUtils writeUtils = new Modbus4jWriteUtils();
        try{
            boolean[] result = readUtils.readInputStatus(1,0,12);
            for (int i=0;i<result.length;i++){
                boolean bool = result[i];
                System.out.println("readInputStatus:"+i+ bool);
            }
//            boolean res = writeUtils.writeCoil(1,3,true);
//            System.out.println("write:" +res);
            Thread.sleep(1000);
            boolean[] result2 = readUtils.readCoilStatus(1,0,8);
            for (int i=0;i<result2.length;i++){
                boolean bool = result2[i];
                System.out.println("readCoilStatus:"+i+ bool);
            }
            Thread.sleep(1000);
//             boolean res = writeUtils.writeRegister(1,8,Short.valueOf("16"));
//            System.out.println("write:" +res);
//            Thread.sleep(1000);
        }catch (ModbusTransportException e){
            System.out.println("exception ModbusTransportException" + e.getMessage()+e.getSlaveId());
        }catch ( ErrorResponseException e){
            System.out.println("exception ErrorResponseException" + e.getMessage());
        }catch (ModbusInitException e){
            System.out.println("exception ModbusInitException" + e.getMessage());
        }catch (InterruptedException e){
            System.out.println("exception InterruptedException" + e.getMessage());
        }
        try{
         short[] shorts = readUtils.readHoldingRegister(1,40069,3);
            for (int i=0;i<shorts.length;i++){
                short bool = shorts[i];
                System.out.println("readHoldingRegister:"+i +" "+ bool + " toUnsignedInt " + toUnsignedInt(bool));
            }
        }catch (ModbusTransportException e){
            System.out.println("exception ModbusTransportException" + e.getMessage()+e.getSlaveId());
        }catch ( ErrorResponseException e){
            System.out.println("exception ErrorResponseException" + e.getMessage());
        }catch (ModbusInitException e){
            System.out.println("exception ModbusInitException" + e.getMessage());
        }

    }

    public static int toUnsignedInt(short x) {
        return ((int) x) & 0xffff;
    }
}
