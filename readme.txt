This is a console application.
The app scan specify directory, filter and group log messages.
Log message format:
2017-09-01 01:11:06 | UserName | Custom Log Message

There are the next command available:

help 
for help

set-count-thread 
set count of thread for processing log files. The command accept one number parameter.
Example:
set-count-thread 5

set-in-dir
specify a directory for scan log files. The command accept one string parameter.
Example:
set-in-dir D:/dir1/dir2/

set-out-file
set a path to the output file with result of analyse. The command accept one string parameter.
Example:
set-out-file D:/dir1/dir2/result.txt

analyse
Run log analysis with parameters. You must to set at least one filter parameter and group parameter.
Filter parameters:
-user : set filter for user name. You can set many user names. Every user name must be with the token.
-start : set filter for start period in format yyyy-MM-dd-HH:mm.
-finish : set filter for finish period in format yyyy-MM-dd-HH:mm.
If you set only one period filter then second set a default value start = LocalDateTime.Min or finish = LocalDateTime.Max
-pattern : set filter for custom message. You can set many patterns. Every pattern must be with the token.
Group parameters:
-groupby : hour, day, month, year or/and username. Every group parameter must be with token.
Example of commands:
analyse -user Nick -start 2017-01-28-12:00 -groupby day -groupby username
analyse -user Nick -finish 2015-01-28-12:00 -groupby year
analyse -start 2010-01-28-12:00 -finish 2017-01-28-12:00 -groupby username
analyse -user Nick -start 2017-01-28-12:00 -finish 2020-02-25-18:00 -groupby month
analyse -user Nick -user Bob -user John -start 2017-01-28-12:00 -groupby username -groupby year
analyse -pattern message -groupby username -groupby day



