package io.github.jithinsethu.superhero.resthero;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.configuration.ProfileManager;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
class HeroApplicationLifecycle {

    private static final Logger logger = Logger.getLogger(HeroApplicationLifecycle.class);

    void onStart(@Observes StartupEvent event) {

        logger.info("");
        logger.info("88        88");
        logger.info("88        88");
        logger.info("88        88");
        logger.info("88aaaaaaaa88   ,adPPYba,  8b,dPPYba,   ,adPPYba,    ,adPPYba,  ,adPPYba,");
        logger.info("88        88  a8P_____88  88P     Y8  a8       8a  a8P_____88  I8[    ");
        logger.info("88        88  8PP         88          8b       d8  8PP           `Y8ba,");
        logger.info("88        88   8b,   ,aa  88           8a,   ,a8   8b,   ,aa  aa    ]8I");
        logger.info("88        88    `Ybbd8'  88             `YbbdP'    `Ybbd8'    `YbbdP'");
        logger.info("");
        logger.info("");
        logger.infof("The application HERO is starting with profile `%s`", ProfileManager.getActiveProfile());

    }

    void onStop(@Observes ShutdownEvent event) {
        logger.info("Heroes Application is Stopping");
    }

}
