# Simple File Watcher using WatchService

## Requirements
- JDK 11
- IntelliJ IDEA

## How to Run
```shell
 java -jar out/artifacts/FileWatcher_main_jar/FileWatcher.main.jar
```

## Example

```shell
➜  FileWatcher git:(master) ✗ java -jar out/artifacts/FileWatcher_main_jar/FileWatcher.main.jar --dir src/main/resources --includes png,txt,jpg
We are watching /Users/ruangguru/IdeaProjects/FileWatcher/src/main/resources
Includes [png, txt, jpg]
Press CTRL+C to exit watching
Halo.txt was created
Halo.txt was modified
Halo.txt was deleted

```

## TODO
- need improvement using Coroutines ?