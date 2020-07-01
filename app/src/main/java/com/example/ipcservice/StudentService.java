package com.example.ipcservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudentService extends Service {
    public StudentService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private IStudentService.Stub mBinder = new IStudentService.Stub() {

        List<Student> students = Collections.synchronizedList(new ArrayList<Student>());

        @Override
        public void addStudent(String id, String name, int age, String gender, String address) throws RemoteException {
            students.add(new Student(id, name, age, gender, address));
        }

        @Override
        public List<Student> getStudents() throws RemoteException {
            return students;
        }

        @Override
        public Student getStudent(String id) throws RemoteException {
            for (Student student : students) {
                if (student.getId().equalsIgnoreCase(id)) {
                    return student;
                }
            }
            return null;
        }
    };
}
