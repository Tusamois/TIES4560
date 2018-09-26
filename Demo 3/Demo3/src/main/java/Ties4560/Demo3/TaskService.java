package Ties4560.Demo3;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import Ties4560.Demo3.exceptionHandling.DataNotFoundException;
import Ties4560.Demo3.exceptionHandling.InvalidIdException;
import Ties4560.Demo3.exceptionHandling.NoValueException;

public class TaskService {

	private static List<Task> list = new ArrayList<Task>(); 
	private static int lastId = 0;
	
	public TaskService() {};
	
	public List<Task> getAllTasks() {
		return list;
	}

	public Task getTask(int groupId, int taskId) {
		
		if (taskId > lastId) {
			throw new InvalidIdException("TaskID "+ taskId +" is not valid");
		}
		if (groupId > GroupService.getLastIndex()) {
			throw new InvalidIdException("GroupID "+ groupId +" is not valid");
		}
		
		Task c = null;
		for (Task task : list) {
			if(task.getTaskId() == taskId) {c=task; break;}
		}
		if (c == null) {
			throw new DataNotFoundException("Task from groupID "+ groupId +" with taskID "+ taskId +"not found");
		}
		return c; 
	}

	public List<Task> getAllTasks(int groupId) {
		if (groupId > GroupService.getLastIndex()) {
			throw new InvalidIdException("GroupID "+ groupId +" is not valid");
		}
		Task c = null;
		List<Task> queryList = new ArrayList<Task>();
		
		for (Task task : list) {
			if(task.getGroupId() == groupId) {queryList.add(task);}
		}
		return queryList; 
	}

	public Task sendTask(int id, Task task) {
		if (id > GroupService.getLastIndex()) {
			throw new InvalidIdException("GroupID "+ id +" is not valid");
		}
		if (task.equals("")) {
			throw new NoValueException("No description in task. Task not added");
		}
		lastId = lastId + 1;
		task.setTaskId(lastId);
		task.setGroupId(id);
		list.add(task);
		return task;
	}

}
