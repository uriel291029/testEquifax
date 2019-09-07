

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class InstallCommand implements Command {

    @Override
    public Map<String, Object> execute(List<String> args) {
        Map<String, Object> result = new LinkedHashMap<>();
        for (String depName : args) {

            Module dep = Module.getInstance(depName);
            install(dep, result);
        }
        return result;
    }

    private Map<String, Object> install(Module current, Map<String, Object> result) {
        if (!current.isInstalled()) {
            current.setInstalled(true);


            for (Module dependency : current.getDependencies()) {
                if (!dependency.isInstalled()) { // not entirely necessary
                    install(dependency, result);
                }

            }
            result.put(current.getName(), "successfully installed");

        }
        else {
            result.put(current.getName(), "is already installed");

        }
        return result;
    }
}
