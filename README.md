# File Backup
A simple file backup program that was built in Kotlin. To use this one must specify the target directory to store the
backups in. Then in a separate folder have the directories and or files to backup listed, detailed below. 
To automate this, you can add it to the *Windows Scheduler* and run it as frequently as required.


#### Backup Directory
In the *Configuration.txt* file is the parameter `BACKUPDIR` next to it is the absolute path to the backup directory, 
e.g.\
`BACKUPDIR C:\CopyTo` replace the *C:\CopyTo*  with your own backup directory. Note if the backup directory doesn't exist it will be created.

##### Compression
Each of the targeted files or directories copied as listed in the *Directories.txt* file can be compressed (`.zip`) by setting 
the `COMPRESSION` config parameter to `true`

#### Targets To Backup

In the *Directories.txt* list, on separate lines, the absolute path of the files or directories you want to back up 
in the *BACKUPDIR* location, like so:

```$xslt
E:\DirToCopy
E:\path\path\path\DirToCopy
E:\file.txt
E:\path\path\path\file.txt
```

## TODO

* Need to sort out making more usable outside of IntelliJ (JAR)
* Logging to file
* Enable two backups to be kept, overwriting the oldest one only per activations