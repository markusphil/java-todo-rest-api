import org.junit.jupiter.api.Test;
import tasks.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void createTask(){
        Task t = new Task("test");
        assertEquals("test",t.name);

    }
}
