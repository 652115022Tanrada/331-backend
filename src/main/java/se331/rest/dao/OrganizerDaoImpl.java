package se331.rest.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import se331.rest.entity.Organizer;
import se331.rest.repository.OrganizerRepository;

@Repository
@RequiredArgsConstructor
public class OrganizerDaoImpl implements OrganizerDao {
    final OrganizerRepository organizerRepository;
    @Override
    public Page<Organizer> getOrganizer(Pageable paeRequest){
        return organizerRepository.findAll(paeRequest);
    }
}