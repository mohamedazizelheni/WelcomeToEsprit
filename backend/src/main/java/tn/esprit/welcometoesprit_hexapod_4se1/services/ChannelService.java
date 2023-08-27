package tn.esprit.welcometoesprit_hexapod_4se1.services;

import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.*;
import org.springframework.stereotype.Service;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.AdsRepository;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.ChannelRepository;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.SocityRepository;

import javax.persistence.EntityNotFoundException;

@Service
public class ChannelService implements IChannelService{
    @Autowired
    ChannelRepository channelRepository;
    @Autowired
    SocityRepository socityRepository ;
    public void ajouterch(Channel channel ,int id ){
        Socity S  = socityRepository.findById(id).get();
        channel.setSocity(S);
        channelRepository.save(channel);
    }
    public void modifierch(Channel channel, int id ){
        Channel c = channelRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(" FAQ not found with id " + id));
        c.setLink(channel.getLink());
        c.setTypeChannel(channel.getTypeChannel());
        channelRepository.save(c);
    }
    public void supprimerch(int id){
        channelRepository.deleteById(id);
    }
}
