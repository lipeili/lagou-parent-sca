package com.lagou.edu.dao;

import com.lagou.edu.pojo.LagouAuthCode;
import com.lagou.edu.pojo.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeDao extends JpaRepository<LagouAuthCode,Long> {
}
