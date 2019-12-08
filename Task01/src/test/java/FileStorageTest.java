import exception.FileAlreadyExistsException;
import org.testng.Assert;
import org.testng.annotations.*;

import static org.testng.Assert.*;

public class FileStorageTest {

    @Test
    public void testConstructor_shouldCreateFileWithCorrectDataValue() {
        FileStorage fileStorageTest = new FileStorage(6);
        assertEquals(fileStorageTest.getMaxSize(), 6, "Expected to create an object with correct data value.");
        assertEquals(fileStorageTest.getAvailableSize(), 6, "Expected to create an object with correct data value.");
    }

    @Test
    public void testConstructor_shouldCreateFileWithNullDataValue() {
        FileStorage fileStorageTest = new FileStorage(0);
        assertEquals(fileStorageTest.getMaxSize(), 0, "Expected to create an object with null data value.");
        assertEquals(fileStorageTest.getAvailableSize(), 0, "Expected to create an object with null data value.");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testConstructor_shouldNotCreateFileWithWrongDataValueAndThrowException() {
        FileStorage fileStorageTest = new FileStorage(-5);
    }

    @Test(groups = "writeTest", expectedExceptions = FileAlreadyExistsException.class)
    public void testWrite_shouldNotWriteFileWithThrowException() throws FileAlreadyExistsException {
        FileStorage fileStorage = new FileStorage(8);
        File file1 = new File("filename1.txt", "1234");
        fileStorage.write(file1);
        fileStorage.write(file1);
    }

    @Test(groups = "writeTest")
    public void testWrite_shouldWriteFileWithEmptyContent() throws FileAlreadyExistsException {
        FileStorage fileStorage = new FileStorage(2);
        File file1 = new File("filename1.txt", "");
        assertTrue(fileStorage.write(file1), "Expected to write file with empty content.");
    }

    @Test(groups = "writeTest", expectedExceptions = IllegalArgumentException.class)
    public void testWrite_shouldNotWriteFileWithEmptyFileName() throws FileAlreadyExistsException {
        FileStorage fileStorage = new FileStorage(2);
        File file1 = new File("", "123");
        fileStorage.write(file1);
    }

    @Test(groups = "writeTest")
    public void testWrite_shouldNotWriteFileWhenFileSizeMoreAvailable() throws FileAlreadyExistsException {
        FileStorage fileStorage = new FileStorage(3);
        File file1 = new File("filename1.txt", "1234");
        assertFalse(fileStorage.write(file1), "Expected not to write file when FileSize more AvailableSize.");
    }

    @Test(groups = "writeTest")
    public void testWrite_shouldWriteFileWhenFileSizeMoreAvailableByOneAndFileSizeIsOdd() throws FileAlreadyExistsException {
        FileStorage fileStorage = new FileStorage(4);
        File file1 = new File("filename1.txt", "12345");
        assertFalse(fileStorage.write(file1), "Expected not to write file when FileSize more AvailableSize.");
    }

    @Test(groups = "writeTest")
    public void testWrite_shouldNotWriteFileInFullStorage() throws FileAlreadyExistsException {
        FileStorage fileStorage = new FileStorage(1);
        File file1 = new File("filename1.txt", "123456");
        File file2 = new File("filename2.txt", "file2 content");
        fileStorage.write(file1);
        assertFalse(fileStorage.write(file2), "Expected not to write file if full storeage.");
    }

    @Test(groups = "writeTest")
    public void testWrite_shouldWriteFileWhenFileSizeLessAvailable() throws FileAlreadyExistsException {
        FileStorage fileStorage = new FileStorage(5);
        File file1 = new File("filename1.txt", "1234");
        assertTrue(fileStorage.write(file1), "Expected to write file when FileSize less AvailableSize.");
    }

    @Test(groups = "writeTest")
    public void testWrite_shouldWriteFileWhenFileSizeEqualsAvailable() throws FileAlreadyExistsException {
        FileStorage fileStorage = new FileStorage(5);
        File file1 = new File("filename1.txt", "12345");
        assertTrue(fileStorage.write(file1), "Expected to write file when FileSize equals AvailableSize.");
    }


    @Test(groups = "writeTest")
    public void testWrite_shouldWriteFileByTimeForEvenAvailableSizeAndEvenFileSize() throws FileAlreadyExistsException {
        FileStorage fileStorage = new FileStorage(8);
        File file1 = new File("filename1.txt", "123456");
        long startTime = System.currentTimeMillis();
        fileStorage.write(file1);
        long finishTime = System.currentTimeMillis();
        long actualTime = finishTime - startTime;
        assertEquals(actualTime, 1800, 100,
                "Expected to write to storage with even availableSize file with even fileSize during 1800 ms.");
    }

    @Test(groups = "writeTest")
    public void testWrite_shouldWriteFileByTimeForOddAvailableSizeAndEvenFileSize() throws FileAlreadyExistsException {
        FileStorage fileStorage = new FileStorage(7);
        File file1 = new File("filename1.txt", "123456");
        long startTime = System.currentTimeMillis();
        fileStorage.write(file1);
        long finishTime = System.currentTimeMillis();
        long actualTime = finishTime - startTime;
        assertEquals(actualTime, 3000, 100,
                "Expected to write to storage with odd availableSize file with even fileSize during 3000 ms.");
    }

    @Test(groups = "writeTest")
    public void testWrite_shouldWriteFileByTimeForEvenAvailableSizeAndOddFileSize() throws FileAlreadyExistsException {
        FileStorage fileStorage = new FileStorage(8);
        File file1 = new File("filename1.txt", "12345");
        long startTime = System.currentTimeMillis();
        fileStorage.write(file1);
        long finishTime = System.currentTimeMillis();
        long actualTime = finishTime - startTime;
        assertEquals(actualTime, 3000, 100,
                "Expected to write to storage with even availableSize file with odd fileSize during 3000 ms.");
    }

    @Test(groups = "writeTest")
    public void testWrite_shouldWriteFileByTimeForOddAvailableSizeAndOddFileSize() throws FileAlreadyExistsException {
        FileStorage fileStorage = new FileStorage(7);
        File file1 = new File("filename1.txt", "12345");
        long startTime = System.currentTimeMillis();
        fileStorage.write(file1);
        long finishTime = System.currentTimeMillis();
        long actualTime = finishTime - startTime;
        assertEquals(actualTime, 1800, 100,
                "Expected to write to storage with odd availableSize file with odd fileSize during 1800 ms.");
    }

    @Test
    public void testIsExistsPositive() throws FileAlreadyExistsException {
        FileStorage fileStorage = new FileStorage(7);
        File file1 = new File("filename1.txt", "12345");
        fileStorage.write(file1);
        assertTrue(fileStorage.isExists(file1.getFilename()), "Expected file is exist in fileStorage.");
    }

    @Test
    public void testIsExistsNegative() {
        FileStorage fileStorage = new FileStorage(7);
        File file1 = new File("filename1.txt", "12345");
        assertFalse(fileStorage.isExists(file1.getFilename()), "Expected file is not exist in fileStorage.");
    }

    @Test
    public void testDeletePositive() throws FileAlreadyExistsException {
        FileStorage fileStorage = new FileStorage(8);
        File file1 = new File("filename1.txt", "123456");
        fileStorage.write(file1);
        assertTrue(fileStorage.delete(file1.getFilename()));
    }

    @Test
    public void testDelete_howChangeAvailablesizeAfterDelete() throws FileAlreadyExistsException {
        FileStorage fileStorage = new FileStorage(8);
        File file1 = new File("filename1.txt", "123456");
        fileStorage.write(file1);
        assertEquals(fileStorage.getAvailableSize(), 2, "Expected availablesize value after writing file.");
        assertTrue(fileStorage.delete(file1.getFilename()), "Expected delete file.");
        assertEquals(fileStorage.getAvailableSize(), 8,
                "Expected availablesize value after delete file will be the same before writing.");
    }

    @Test
    public void testDeleteNegative() {
        FileStorage fileStorage = new FileStorage(8);
        File file1 = new File("filename1.txt", "123456");
        assertFalse(fileStorage.delete(file1.getFilename()), "Expected not added file will not be deleted.");
    }

    @Test
    public void testGetFilesPositive() throws FileAlreadyExistsException {
        FileStorage fileStorage = new FileStorage(8);
        File file1 = new File("filename1.txt", "1234");
        File file2 = new File("filename2.txt", "12");
            fileStorage.write(file1);
            fileStorage.write(file2);
        assertTrue(fileStorage.getFiles().get(0).getFilename().equals("filename1.txt"),
                "Expected to get fileName of file1 from fileStorage.");
        assertTrue(fileStorage.getFiles().get(1).getFilename().equals("filename2.txt"),
                "Expected to get fileName of file1 from fileStorage.");
    }

    @Test
    public void testGetFilePositive() throws FileAlreadyExistsException {
        FileStorage fileStorage = new FileStorage(8);
        File file1 = new File("filename1.txt", "1234");
        fileStorage.write(file1);
        assertTrue(fileStorage.getFile("filename1.txt").getFilename().equals("filename1.txt"), "Expected to get fileName");
    }

    @Test
    public void testGetAvailableSize() {
        FileStorage fileStorage = new FileStorage(8);
        assertEquals(fileStorage.getAvailableSize(), 8, "Expected to get availableSize value in method .getAvailableSize().");
    }

    @Test
    public void testGetAvailableSizeAfterWriteOddContent() throws FileAlreadyExistsException {
        FileStorage fileStorage = new FileStorage(8);
        File file1 = new File("filename1.txt", "12345");
        fileStorage.write(file1);
        assertEquals(fileStorage.getAvailableSize(), 3,
                "Expected to get availableSize after write file with odd content in method .getAvailableSize()");
    }

    @Test
    public void testGetAvailableSizeAfterWriteEvenContent() throws FileAlreadyExistsException {
        FileStorage fileStorage = new FileStorage(8);
        File file1 = new File("filename1.txt", "1234");
        fileStorage.write(file1);
        assertEquals(fileStorage.getAvailableSize(), 4,
                "Expected to get availableSize after write file with even content in method .getAvailableSize()");
    }

    @Test
    public void testGetMaxSize() {
        FileStorage fileStorage = new FileStorage(8);
        assertEquals(fileStorage.getMaxSize(), 8, "Expected to get maxSize value in method .getMaxSize().");
    }

}