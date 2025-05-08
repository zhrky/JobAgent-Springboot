package chat.chat.service;

import chat.chat.model.Preference;
import chat.chat.repository.PreferenceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class PreferenceService {

    private final PreferenceRepository preferenceRepository;

    public PreferenceService(PreferenceRepository preferenceRepository) {
        this.preferenceRepository = preferenceRepository;
    }

    public void savePreference(String userId, Preference preference) {
        Optional<Preference> existingPreference = preferenceRepository.findByUserId(userId);
        Preference preferenceToSave = existingPreference.orElse(new Preference());

        preferenceToSave.setUserId(userId);
        preferenceToSave.setPosition(preference.getPosition());
        preferenceToSave.setLocation(preference.getLocation());
        preferenceToSave.setWorkType(preference.getWorkType());

        preferenceRepository.save(preferenceToSave);
    }

    public Optional<Preference> getPreferenceByUserId(String userId) {
        return preferenceRepository.findByUserId(userId);
    }

    public List<Preference> getPreferencesByUserId(String userId) {
        return preferenceRepository.findAllByUserId(userId);
    }
}