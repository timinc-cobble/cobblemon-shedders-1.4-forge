package us.timinc.mc.cobblemon.shedders.config

import com.google.gson.GsonBuilder
import us.timinc.mc.cobblemon.shedders.Shedders
import java.io.File
import java.io.FileReader
import java.io.PrintWriter

class Config {
    val shedders: Map<String, String> = mutableMapOf()

    class Builder {
        companion object {
            fun load(): Config {
                val gson = GsonBuilder()
                    .disableHtmlEscaping()
                    .setPrettyPrinting()
                    .create()

                var config = Config()
                val configFile = File("config/${Shedders.MOD_ID}.json")
                configFile.parentFile.mkdirs()

                if (configFile.exists()) {
                    try {
                        val fileReader = FileReader(configFile)
                        config = gson.fromJson(fileReader, Config::class.java)
                        fileReader.close()
                    } catch (e: Exception) {
                        println("Error reading config file")
                    }
                }

                val pw = PrintWriter(configFile)
                gson.toJson(config, pw)
                pw.close()

                return config
            }
        }
    }
}