package com.project.resourcivo.repository;

import com.project.resourcivo.model.BookIssue;
import com.project.resourcivo.model.IssueStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookIssueRepository extends JpaRepository<BookIssue, Long> {
    List<BookIssue> findByStudentIdAndStatus(Long studentId, IssueStatus status);

    List<BookIssue> findByStudentId(Long studentId);

    List<BookIssue> findByStatus(IssueStatus status);

    List<BookIssue> findByStudentIdAndStatusIn(Long studentId, List<IssueStatus> statuses);

    List<BookIssue> findByBookId(Long bookId);
}
