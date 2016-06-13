package com.silverlake.dms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.silverlake.dms.repositories.entities.DeviceJournal;

public interface DeviceJournalLocal extends JpaRepository<DeviceJournal, Integer> {

}
