package com.tcl.user.repository;

import com.tcl.user.model.TvSetting;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TvSettingRepository extends JpaRepository<TvSetting,Long>{

	TvSetting findByUsername(String username);


//    void delete(String username);
    @Override
    void delete(TvSetting t);

}
