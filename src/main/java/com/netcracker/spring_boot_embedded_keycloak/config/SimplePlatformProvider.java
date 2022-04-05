package com.netcracker.spring_boot_embedded_keycloak.config;

import lombok.extern.slf4j.Slf4j;
import org.keycloak.platform.PlatformProvider;
import org.keycloak.services.ServicesLogger;

import java.io.File;

@Slf4j
public class SimplePlatformProvider implements PlatformProvider {

   Runnable shutdownHook;

   @Override
   public void onStartup(Runnable startupHook) {
      startupHook.run();
   }

   @Override
   public void onShutdown(Runnable shutdownHook) {
      this.shutdownHook = shutdownHook;
   }

   @Override
   public void exit(Throwable cause) {
      ServicesLogger.LOGGER.fatal(cause);
      log.error("Exiting because of error", cause);
      exit(1);
   }

   @Override
   public File getTmpDirectory() {
      return new File(System.getProperty("java.io.tmpdir"));
   }

   private void exit(int status) {
      new Thread(() -> System.exit(status)).start();
   }

}