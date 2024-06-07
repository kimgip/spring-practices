package com.poscodx.guestbook.service;

import java.util.List;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.poscodx.guestbook.repository.GuestbookLogRepository;
import com.poscodx.guestbook.repository.GuestbookRepositoryWithJdbcContext;
import com.poscodx.guestbook.vo.GuestbookVo;

@Service
public class GuestBookService {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private GuestbookRepositoryWithJdbcContext guestbookRepository;

	private GuestbookLogRepository guestbookLogRepository;
	public List<GuestbookVo> getContentsList() {
		return guestbookRepository.findAll();
	}
	
	public void deleteContents(Long no, String password) {
		guestbookRepository.deleteByNoAndPassword(no, password);
	}
	
	public void addContents(GuestbookVo vo) {
		// 트랜잭션 동기(Connection) 초기화
		TransactionSynchronizationManager.initSynchronization();
		Connection conn = DataSourceUtils.getConnection(dataSource);
		
		try {
			// TX:BEGIN
			conn.setAutoCommit(false);
			
			int count = guestbookLogRepository.update();
			
			if(count == 0) {
				guestbookLogRepository.insert();
			}
			
			guestbookRepository.insert(vo);
			
			// TX:END(SUCCESS)
			conn.commit();
		} catch(Throwable e) {
			// TX:END(FAIL)
			try {
				conn.rollback();
			} catch(SQLException ignored) {
			}
			
		} finally {
			DataSourceUtils.releaseConnection(conn, dataSource);
		}
	}
}
