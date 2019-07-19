package app;

import com.serotonin.modbus4j.ProcessImageListener;

/**
 * @Author guc
 * @Date 2019/7/17 11:42
 * @Description TODO
 */
public class BasicProcessImageListener implements ProcessImageListener {

    public void coilWrite(int offset, boolean oldValue, boolean newValue) {
        System.out.println("Coil at " + offset + " was set from " + oldValue + " to " + newValue);
    }

    public void holdingRegisterWrite(int offset, short oldValue, short newValue) {
        System.out.println("HoldRrgister at " + offset + " was set from " + oldValue + " to " + newValue);
    }

}
