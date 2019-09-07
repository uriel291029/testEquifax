

import java.util.List;
import java.util.Map;

public interface Command {
    
    Map<String, Object> execute(List<String> args);
}
