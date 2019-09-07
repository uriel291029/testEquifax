


import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DependCommand implements Command {

    @Override
    public Map<String, Object> execute(List<String> args) {
        String depName = args.get(0);

        Module current = Module.getInstance(depName);

        for (String strDependency : args.subList(1, args.size())) {
            Module dependency = Module.getInstance(strDependency);
            current.addDependency(dependency);
            dependency.addDependent(current);
        }
        return Collections.emptyMap();
    }
}
