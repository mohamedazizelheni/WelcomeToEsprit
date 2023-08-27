package tn.esprit.welcometoesprit_hexapod_4se1.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Channel;

@Repository
public interface ChannelRepository extends CrudRepository<Channel,Integer> {
}
