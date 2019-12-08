import org.testng.annotations.*;

import static org.testng.Assert.*;

public class FileTest {

    @DataProvider(name = "wrongFileName")
    public Object[] incorrectFileNameSymbol(){
        return new Object[] {"$.txt", "{}.txt", "[].txt", "&.txt", "%.txt", "/.txt", "#.txt", "*.txt"};
    }

    @Test(groups = "fileConstructorTest")
    public void testConstructor_createFileWithCorrectData() {
        File file = new File("filename.txt", "12345678");
        assertEquals(file.getFilename(), "filename.txt", "Expected to get filename.");
        assertEquals(file.getContent(), "12345678", "Expected to get content.");
    }

    @Test(groups = "fileConstructorTest", expectedExceptions = IllegalArgumentException.class)
    public void testConstructor_createFileWithEmptyName() {
        File file = new File("", "12345678");
    }

    @Test(groups = "fileConstructorTest", dataProvider = "wrongFileName",
            expectedExceptions = IllegalArgumentException.class)
    public void testConstructor_createFileWithWrongSymbolInFilename(String wrongFileName) {
        File file = new File(wrongFileName, "12345678");
    }

    @Test
    public void testGetExtension_getExtensionWithOnePoint() {
        File file = new File("filename.txt", "12345678");
        assertEquals(file.getExtension(),"txt", "Expected to get extension from .getExtension().");
    }

    @Test(timeOut = 5)
    public void testGetExtension_getExtensionWithTwoPoint() {
        File file = new File("file.name.txt", "12345678");
        assertEquals(file.getExtension(),"txt", "Expected to get extension from .getExtension().");
    }

    @Test
    public void testGetExtension_getExtensionWithThreePoint() {
        File file = new File("file.name.txt", "12345678");
        assertEquals(file.getExtension(),"txt", "Expected to get extension from .getExtension().");
    }

    @Test
    public void testGetExtensionFilenameWithoutExtension() {
        File file = new File("filename", "12345678");
        assertEquals(file.getExtension(),"", "Expected to get empty string from .getExtension().");
    }

    @Test
    public void testGetExtension_getExtensionWithFirstPartOfFilename() {
        File file = new File(".gitignore", "12345678");
        assertEquals(file.getExtension(),"gitignore", "Expected to get extension from .getExtension().");
    }

    @Test
    public void testGetSize_withEvenContent() {
        File file = new File("filename", "12345678");
        assertEquals(file.getSize(),8, "Expected to get size from .getSize() with even content.");
    }

    @Test
    public void testGetSize_withOddContent() {
        File file = new File("filename.txt", "1234567");
        assertEquals(file.getSize(),7, "Expected to get size from .getSize() with odd content.");
    }

    @Test
    public void testGetSize_withNullContent() {
        File file = new File("filename.txt", "");
        assertEquals(file.getSize(),0, "Expected to get size from .getSize() with null content.");
    }

    @Test
    public void testGetContent() {
        File file = new File("filename.txt", "1234567");
        assertEquals(file.getContent(),"1234567", "Expected to get content from .getContent().");
    }

    @Test
    public void testGetContent_AfterGetExtension() {
        File file = new File("filename.txt", "1234567");
        file.getExtension();
        assertEquals(file.getContent(),"1234567");
    }

    @Test
    public void testGetFilename_shouldGetFilename() {
        File file = new File("filename.txt", "1234567");
        assertEquals(file.getFilename(),"filename.txt", "Expected to get fileName from .getFilename().");
    }

}