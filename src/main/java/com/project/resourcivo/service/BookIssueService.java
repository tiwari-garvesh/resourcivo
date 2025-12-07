package com.project.resourcivo.service;

import com.project.resourcivo.dto.BookIssueRequestDTO;
import com.project.resourcivo.dto.BookIssueResponseDTO;
import com.project.resourcivo.model.*;
import com.project.resourcivo.repository.BookIssueRepository;
import com.project.resourcivo.repository.LibraryBookRepository;
import com.project.resourcivo.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookIssueService {

    private final BookIssueRepository issueRepo;
    private final LibraryBookRepository bookRepo;
    private final StudentRepository studentRepo;

    public BookIssueService(BookIssueRepository issueRepo, LibraryBookRepository bookRepo,
            StudentRepository studentRepo) {
        this.issueRepo = issueRepo;
        this.bookRepo = bookRepo;
        this.studentRepo = studentRepo;
    }

    @Transactional
    public BookIssueResponseDTO requestIssue(BookIssueRequestDTO dto) {
        Student student = studentRepo.findById(dto.getStudentId())
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));
        LibraryBook book = bookRepo.findById(dto.getBookId())
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        if (Boolean.TRUE.equals(book.getIsIssued())) {
            throw new IllegalStateException("Book is already issued");
        }

        BookIssue issue = new BookIssue();
        issue.setStudent(student);
        issue.setBook(book);
        issue.setStatus(IssueStatus.PENDING);
        issue.setRequestDate(LocalDate.now());

        return toResponse(issueRepo.save(issue));
    }

    @Transactional
    public BookIssueResponseDTO approveIssue(Long issueId) {
        BookIssue issue = issueRepo.findById(issueId)
                .orElseThrow(() -> new IllegalArgumentException("Issue request not found"));

        if (issue.getStatus() != IssueStatus.PENDING) {
            throw new IllegalStateException("Request is not in PENDING state");
        }

        LibraryBook book = issue.getBook();
        if (Boolean.TRUE.equals(book.getIsIssued())) {
            throw new IllegalStateException("Book is already issued to someone else");
        }

        issue.setStatus(IssueStatus.ISSUED);
        issue.setIssueDate(LocalDate.now());
        issue.setExpectedReturnDate(LocalDate.now().plusWeeks(2)); // Default 2 weeks

        book.setIsIssued(true);
        bookRepo.save(book);

        return toResponse(issueRepo.save(issue));
    }

    @Transactional
    public BookIssueResponseDTO rejectIssue(Long issueId) {
        BookIssue issue = issueRepo.findById(issueId)
                .orElseThrow(() -> new IllegalArgumentException("Issue request not found"));

        if (issue.getStatus() != IssueStatus.PENDING) {
            throw new IllegalStateException("Request is not in PENDING state");
        }

        issue.setStatus(IssueStatus.REJECTED);
        return toResponse(issueRepo.save(issue));
    }

    @Transactional
    public BookIssueResponseDTO requestReturn(Long issueId) {
        BookIssue issue = issueRepo.findById(issueId)
                .orElseThrow(() -> new IllegalArgumentException("Issue request not found"));

        if (issue.getStatus() != IssueStatus.ISSUED) {
            throw new IllegalStateException("Book is not currently issued");
        }

        issue.setStatus(IssueStatus.RETURN_REQUESTED);
        return toResponse(issueRepo.save(issue));
    }

    @Transactional
    public BookIssueResponseDTO confirmReturn(Long issueId) {
        BookIssue issue = issueRepo.findById(issueId)
                .orElseThrow(() -> new IllegalArgumentException("Issue request not found"));

        if (issue.getStatus() != IssueStatus.RETURN_REQUESTED) {
            throw new IllegalStateException("Return request not found");
        }

        LibraryBook book = issue.getBook();
        book.setIsIssued(false);
        bookRepo.save(book);

        issue.setStatus(IssueStatus.RETURNED);
        issue.setReturnDate(LocalDate.now());

        return toResponse(issueRepo.save(issue));
    }

    @Transactional
    public BookIssueResponseDTO rejectReturn(Long issueId) {
        BookIssue issue = issueRepo.findById(issueId)
                .orElseThrow(() -> new IllegalArgumentException("Issue request not found"));

        if (issue.getStatus() != IssueStatus.RETURN_REQUESTED) {
            throw new IllegalStateException("Return request not found");
        }

        // Revert to ISSUED as request was declined
        issue.setStatus(IssueStatus.ISSUED);
        return toResponse(issueRepo.save(issue));
    }

    public List<BookIssueResponseDTO> getEffectiveIssuedBooks(Long studentId) {
        // Returns books that are currently out (ISSUED or RETURN_REQUESTED)
        return issueRepo
                .findByStudentIdAndStatusIn(studentId, List.of(IssueStatus.ISSUED, IssueStatus.RETURN_REQUESTED))
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public List<BookIssueResponseDTO> getIssueHistory(Long studentId) {
        return issueRepo.findByStudentId(studentId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public List<BookIssueResponseDTO> getBookHistory(Long bookId) {
        return issueRepo.findByBookId(bookId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public List<BookIssueResponseDTO> getAllPendingRequests() {
        return issueRepo.findByStatus(IssueStatus.PENDING).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private BookIssueResponseDTO toResponse(BookIssue e) {
        BookIssueResponseDTO r = new BookIssueResponseDTO();
        r.setId(e.getId());
        r.setStudentId(e.getStudent().getId());
        r.setStudentName(e.getStudent().getFirstName() + " " + e.getStudent().getLastName());
        r.setBookId(e.getBook().getId());
        r.setBookName(e.getBook().getName());
        r.setStatus(e.getStatus());
        r.setRequestDate(e.getRequestDate());
        r.setIssueDate(e.getIssueDate());
        r.setExpectedReturnDate(e.getExpectedReturnDate());
        r.setReturnDate(e.getReturnDate());
        return r;
    }
}
