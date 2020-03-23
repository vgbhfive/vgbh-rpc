package cn.vgbhfive.vgbhexample;

/**
 * @author Vgbh
 * @date 2020/3/20 21:01
 */
public class CalcServiceImpl implements CalcService{

    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int minus(int a, int b) {
        return a - b;
    }
}
