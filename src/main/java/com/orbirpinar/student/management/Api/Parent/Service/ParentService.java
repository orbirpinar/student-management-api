package com.orbirpinar.student.management.Api.Parent.Service;

import com.orbirpinar.student.management.Api.Parent.Entity.Parent;

import java.util.List;

public interface ParentService {

   List<Parent> getAll();

   Parent getById(String parentId);

   List<Parent> createParentByStudent(String studentId, Parent parent);

   List<Parent> getParentByStudent(String studentId);
}
