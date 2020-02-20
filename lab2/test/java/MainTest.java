import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;


public class MainTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void TestAdd() throws IllegalAccessException, InvocationTargetException, IOException, InstantiationException, NoSuchMethodException, ClassNotFoundException {
        String[] arg = {"testAdd.txt"};
        Main.main(arg);
        Assert.assertEquals(1427d, Double.parseDouble(outContent.toString()), 0);
    }

    @Test
    public void TestMul() throws IllegalAccessException, InvocationTargetException, IOException, InstantiationException, NoSuchMethodException, ClassNotFoundException {
        String[] arg = {"testMul.txt"};
        Main.main(arg);
        Assert.assertEquals(50d, Double.parseDouble(outContent.toString()), 0);
    }

    @Test
    public void TestDiv() throws IllegalAccessException, InvocationTargetException, IOException, InstantiationException, NoSuchMethodException, ClassNotFoundException {
        String[] arg = {"testDiv.txt"};
        Main.main(arg);
        Assert.assertEquals(2d, Double.parseDouble(outContent.toString()), 0);
    }

    @Test
    public void TestPop() throws IllegalAccessException, InvocationTargetException, IOException, InstantiationException, NoSuchMethodException, ClassNotFoundException {
        String[] arg = {"testPop.txt"};
        Main.main(arg);
        Assert.assertEquals(5d, Double.parseDouble(outContent.toString()), 0);
    }

    @Test
    public void TestPush() throws IllegalAccessException, InvocationTargetException, IOException, InstantiationException, NoSuchMethodException, ClassNotFoundException {
        String[] arg = {"testPush.txt"};
        Main.main(arg);
        Assert.assertEquals(1234325.76d, Double.parseDouble(outContent.toString()), 0);
    }

    @Test
    public void TestDefine() throws IllegalAccessException, InvocationTargetException, IOException, InstantiationException, NoSuchMethodException, ClassNotFoundException {
        String[] arg = {"testDefine.txt"};
        Main.main(arg);
        Assert.assertEquals(98.54d, Double.parseDouble(outContent.toString()), 0);
    }

    @Test
    public void TestSqrt() throws IllegalAccessException, InvocationTargetException, IOException, InstantiationException, NoSuchMethodException, ClassNotFoundException {
        String[] arg = {"testSqrt.txt"};
        Main.main(arg);
        Assert.assertEquals(11d, Double.parseDouble(outContent.toString()), 0);
    }

    @Test
    public void TestSub() throws IllegalAccessException, InvocationTargetException, IOException, InstantiationException, NoSuchMethodException, ClassNotFoundException {
        String[] arg = {"testSub.txt"};
        Main.main(arg);
        Assert.assertEquals(11d, Double.parseDouble(outContent.toString()), 0);
    }
}