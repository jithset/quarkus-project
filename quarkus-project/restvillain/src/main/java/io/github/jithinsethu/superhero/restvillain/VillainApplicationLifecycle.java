package io.github.jithinsethu.superhero.restvillain;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.configuration.ProfileManager;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
class VillainApplicationLifecycle {

    private static final Logger logger = Logger.getLogger(VillainApplicationLifecycle.class);

    void onStart(@Observes StartupEvent event) {

        logger.info(" .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .-----------------.");
        logger.info("| .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |");
        logger.info("| | ____   ____  | || |     _____    | || |   _____      | || |   _____      | || |      __      | || |     _____    | || | ____  _____  | |");
        logger.info("| ||_  _| |_  _| | || |    |_   _|   | || |  |_   _|     | || |  |_   _|     | || |     //\\     | || |    |_   _|   | || ||_  \\|_   _| | |");
        logger.info("| |  \\\\  / /   | || |      | |     | || |    | |       | || |    | |       | || |    // /\\    | || |      | |     | || |  |  \\ | |   | |");
        logger.info("| |   \\\\/ /    | || |      | |     | || |    | |   _   | || |    | |   _   | || |   / ____ \\  | || |      | |     | || |  | |\\ \\| | | |");
        logger.info("| |    \\ '/     | || |     _| |_    | || |   _| |__//|  | || |   _| |__//|  | || | _////   \\\\_| || |     _| |_    | || | _| |_\\  |_  | |");
        logger.info("| |     \\/      | || |    |_____|   | || |  |________|  | || |  |________|  | || ||____|  |____|| || |    |_____|   | || ||_____|\\____|| |");
        logger.info("| |              | || |              | || |              | || |              | || |              | || |              | || |              | |");
        logger.info("| '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |");
        logger.info(" '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------' ");
        logger.infof("The application VILLAIN is starting with profile `%s`", ProfileManager.getActiveProfile());

    }

    void onStop(@Observes ShutdownEvent event) {
        logger.info("Villain Application is Stopping");
    }

}
