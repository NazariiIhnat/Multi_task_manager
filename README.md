# Multi_task_manager
To try this application open Multi_task_manager/MultiTaskManager-1.0-SNAPSHOT.jar (JRE mus be installed);
TestRemoteDB is old test folder. You can delete it.

Used technologies: Java Core, Hibernate, Spring, MySQL (remote server located on db4free), Maven, Swing;

To show you the logic of this application there are registered 2 accounts
- first account:
   nickname: boss password: boss1234;
- second account:
   nickname: employee password employee.

The boss account has boss permissions so you can send task for employee(this task is immutable for employee. He can only change status of task(done/undone))
ADD TASK - RMB on the date. 
DELETE/UPDATE TASK - RMB on task in table and delete it.
By default boss mode password 1. To disable boss mode enter 2(in boss mode menu);
You can set your own db by setting db url, username and password in hibernate.cfg.xml file;
