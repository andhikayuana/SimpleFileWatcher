package command

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import com.github.ajalt.clikt.parameters.options.split
import com.github.ajalt.clikt.parameters.types.path
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardWatchEventKinds
import java.nio.file.WatchService
import kotlin.io.path.absolutePathString

class Watch : CliktCommand(printHelpOnEmptyArgs = true) {

    val dir by option("-d", "--dir", help = "The directory you want to watch")
        .path(mustExist = true, mustBeReadable = true)
        .required()

    val includeTypes by option(
        "-i",
        "--includes",
        help = "File type you want to include with comma separated e.g. --includes png,webp,jpg"
    )
        .split(",")
        .required()


    override fun run() {

        println("We are watching ${dir.absolutePathString()}")
        println("Includes $includeTypes")
        println("Press CTRL+C to exit watching")


        val path = Paths.get(dir.absolutePathString())
        val watcher = path.watch()

        while (true) {
            //The watcher blocks until an event is available
            val key = watcher.take()

            //Now go through each event on the folder
            key.pollEvents().forEach {
                //Print output according to the event
                when (it.kind().name()) {
                    StandardWatchEventKinds.ENTRY_CREATE.name() -> println("${it.context()} was created")
                    StandardWatchEventKinds.ENTRY_MODIFY.name() -> println("${it.context()} was modified")
                    StandardWatchEventKinds.OVERFLOW.name() -> println("${it.context()} overflow")
                    StandardWatchEventKinds.ENTRY_DELETE.name() -> println("${it.context()} was deleted")
                }
            }
            //Call reset() on the key to watch for future events
            key.reset()
        }

    }
}

private fun Path.watch(): WatchService {
    //Create a watch service
    val watchService = fileSystem.newWatchService()

    //Register the service, specifying which events to watch
    register(
        watchService,
        StandardWatchEventKinds.ENTRY_CREATE,
        StandardWatchEventKinds.ENTRY_MODIFY,
        StandardWatchEventKinds.OVERFLOW,
        StandardWatchEventKinds.ENTRY_DELETE
    )

    //Return the watch service
    return watchService
}