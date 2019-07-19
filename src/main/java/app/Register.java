package app;

import com.serotonin.modbus4j.BasicProcessImage;

/**
 * @Author guc
 * @Date 2019/7/17 11:42
 * @Description TODO
 */
public class Register {
    static BasicProcessImage getModscanProcessImage(int slaveId) {
        BasicProcessImage processImage = new BasicProcessImage(slaveId);
        processImage.setInvalidAddressValue(Short.MIN_VALUE);
        //创建可读写开关量
        processImage.setCoil(0, true);
        processImage.setCoil(1, false);
        processImage.setCoil(2, true);
        processImage.setCoil(3, false);
        processImage.setCoil(4, true);
        processImage.setCoil(5, false);
        processImage.setCoil(6, true);
        processImage.setCoil(7, true);
        processImage.setCoil(8, true);
        processImage.setCoil(9, true);

        //创建只读开关量
        processImage.setInput(0, false);
        processImage.setInput(1, false);
        processImage.setInput(2, true);
        processImage.setInput(3, false);
        processImage.setInput(4, true);
        processImage.setInput(5, true);
        processImage.setInput(6, true);
        processImage.setInput(7, false);
        processImage.setInput(8, true);
        processImage.setInput(9, true);

        //创建 保持寄存器
        processImage.setHoldingRegister(0, (short) 1);
        processImage.setHoldingRegister(1, (short) 10);
        processImage.setHoldingRegister(2, (short) 100);
        processImage.setHoldingRegister(3, (short) 1000);
        processImage.setHoldingRegister(4, (short) 10000);
        processImage.setHoldingRegister(5, (short) 10000);
        processImage.setHoldingRegister(6, (short) 10000);
        processImage.setHoldingRegister(7, (short) 10000);
        processImage.setHoldingRegister(8, (short) 10000);
        processImage.setHoldingRegister(9, (short) 10000);

        //创建 只读寄存器
        processImage.setInputRegister(0, (short) 10000);
        processImage.setInputRegister(1, (short) 1000);
        processImage.setInputRegister(2, (short) 100);
        processImage.setInputRegister(3, (short) 10);
        processImage.setInputRegister(4, (short) 1);
        processImage.setInputRegister(5, (short) 1);
        processImage.setInputRegister(6, (short) 1);
        processImage.setInputRegister(7, (short) 1);
        processImage.setInputRegister(8, (short) 1);
        processImage.setInputRegister(9, (short) 1);

        processImage.addListener(new BasicProcessImageListener());
        return processImage;
    }

}
