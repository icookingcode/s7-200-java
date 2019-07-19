package app.test;

import app.TcpMaster;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.msg.*;

import java.util.Arrays;

/**
 * @Author guc
 * @Date 2019/7/18 9:36
 * @Description TODO
 */
public class main {
    //MODBUS网络上从站地址
    private final static int SLAVE_ADDRESS = 1;
    //串行口波特率
    private final static int BAUD_RATE = 9600;

    public static void main(String[] args) {
        ModbusMaster master = TcpMaster.getMaster();
        System.out.println("读开关量型的输入信号");
        readDiscreteInputTest(master,SLAVE_ADDRESS,0,8);
//        System.out.println("批量写数据到保持寄存器");
//        writeRegistersTest(master,SLAVE_ADDRESS, 21, new short[]{0x09,0xa} );

        System.out.println("读保持寄存器上的内容");
        readHoldingRegistersTest(master,SLAVE_ADDRESS,67,100);
    }


    /**
     * 读开关量型的输入信号
     *
     * @param master  主站
     * @param slaveId 从站地址
     * @param start   起始偏移量
     * @param len     待读的开关量的个数
     */

    private static void readDiscreteInputTest(ModbusMaster master, int slaveId, int start, int len) {

        try {

            ReadDiscreteInputsRequest request = new ReadDiscreteInputsRequest(slaveId, start, len);

            ReadDiscreteInputsResponse response = (ReadDiscreteInputsResponse) master.send(request);


            if (response.isException())

                System.out.println("Exception response: message=" + response.getExceptionMessage());

            else

                System.out.println(Arrays.toString(response.getBooleanData()));

        } catch (ModbusTransportException e) {
            e.printStackTrace();
        }

    }

    /**

     * 批量写数据到保持寄存器

     * @param master 主站

     * @param slaveId 从站地址

     * @param start 起始地址的偏移量

     * @param values 待写数据

     */

    public static void writeRegistersTest(ModbusMaster master, int slaveId, int start, short[] values) {

        try {

            WriteRegistersRequest request = new WriteRegistersRequest(slaveId, start, values);

            WriteRegistersResponse response = (WriteRegistersResponse) master.send(request);



            if (response.isException())

                System.out.println("Exception response: message=" + response.getExceptionMessage());

            else

                System.out.println("Success");

        }

        catch (ModbusTransportException e) {

            e.printStackTrace();

        }

    }

    /**

     * 读保持寄存器上的内容

     * @param master 主站

     * @param slaveId 从站地址

     * @param start 起始地址的偏移量

     * @param len 待读寄存器的个数

     */

    private static void readHoldingRegistersTest(ModbusMaster master, int slaveId, int start, int len) {

        try {

            ReadHoldingRegistersRequest request = new ReadHoldingRegistersRequest(slaveId, start-1, len);

            ReadHoldingRegistersResponse response = (ReadHoldingRegistersResponse) master.send(request);



            if (response.isException())

                System.out.println("Exception response: message=" + response.getExceptionMessage());

            else

                System.out.println(Arrays.toString(response.getShortData()));

        }

        catch (ModbusTransportException e) {

            e.printStackTrace();

        }

    }

}
