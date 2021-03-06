/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Zoe Lee (Kihira)
 *
 * See LICENSE for full License
 */

package uk.kihira.foxlib

import java.io.File

import net.minecraftforge.common.config.Configuration
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import org.apache.logging.log4j.{LogManager, Logger}
import uk.kihira.foxlib.proxy.CommonProxy

object FoxLib {

    final val MOD_ID = "foxlib"
    final val logger: Logger = LogManager.getLogger(MOD_ID)

    var showCollisionBoxes: Boolean = false
    var fancySkulls: Boolean = true

    //@SidedProxy(clientSide = "uk.kihira.foxlib.proxy.ClientProxy", serverSide = "uk.kihira.foxlib.proxy.CommonProxy")
    var proxy: CommonProxy = null

    //@EventHandler
    def onPreInit(e: FMLPreInitializationEvent) {
        loadConfig(e.getSuggestedConfigurationFile)
        proxy.registerEventHandlers()
    }

    private def loadConfig(file: File) = {
        val config: Configuration = new Configuration(file)
        showCollisionBoxes = config.getBoolean("Show collision boxes", "client", false, "Shows the collision boxes for blocks")
        fancySkulls = config.getBoolean("Fancy skull rendering", "client", true, "Renders player heads with the hair layer and 3D in inventory")

        if (config.hasChanged) config.save()
    }
}
