package util;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

public class DroolsUtil<T> {

    private KieSession kieSession = null;

    public DroolsUtil(String filepath) throws IllegalAccessException {
        this.kieSession = getSession(filepath);
    }

    private KieSession getSession(String filepath) throws IllegalAccessException{

        KieServices kieServices = KieServices.Factory.get();
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();

        File file = Paths.get(filepath).toFile();

        try {
            kieFileSystem.write("src/main/resources/" + file.getPath(), kieServices.getResources().newInputStreamResource(new FileInputStream(file)));

            KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem).buildAll();

            Results results = kieBuilder.getResults();
            if (results.hasMessages(Message.Level.ERROR)) throw new IllegalAccessException(">>>ルールファイルの記述が誤っています");

            KieContainer kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
            kieSession = kieContainer.newKieSession();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return kieSession;
    }


    public void executeRule(T t) {
        FactHandle factHandle = kieSession.insert(t);
        kieSession.fireAllRules();
        kieSession.delete(factHandle);
    }
}
