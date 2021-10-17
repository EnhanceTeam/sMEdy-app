package com.example.smedy.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.smedy.model.Music;

import java.util.ArrayList;

public class MusicRepository {
    private static MusicRepository repository;

    public MusicRepository(){}

    public static MusicRepository getInstance(){
        if(repository==null){
            repository = new MusicRepository();
        }
        return repository;
    }

    ArrayList<Music> musicList = new ArrayList<Music>(){
        {
            add(new Music("Acoustic Guitars Ambient","acoustic-guitars-ambient-uplifting.mp3","cc7cf69a-82bb-44bb-9401-b6c1f8eb8a1a"));
            add(new Music("Cali","cali.mp3","4b99f7a4-6c3f-419d-898a-2314518b0f6b"));
            add(new Music("Cancion Triste","cancion-triste.mp3","a66a894e-b825-4548-ba0b-ecdf1185dc79"));
            add(new Music("Chilled Acoustic Indie Folk","chilled-acoustic-indie-folk.mp3","008027a3-f550-4081-a5fc-64443cd6c3a1"));
            add(new Music("Cinematic Fairy Tale","cinematic-fairy-tale-story-main.mp3","c0bffdf4-308c-4296-b33e-6d654bec0cfd"));
            add(new Music("Goodbye Stress","goodbye-stress.mp3","baa7b5d7-8078-440a-be48-acb6869a14d0"));
            add(new Music("In The Forest","in-the-forest-ambient.mp3","6a90033c-1e42-439c-a2ea-4ff0c8e0195e"));
            add(new Music("Melody Of Nature","melody-of-nature-main.mp3","093bd8e3-e680-4ef0-95e0-72d1d2145981"));
            add(new Music("Modular","modular-ambient.mp3","13975748-f153-461c-94b3-092fa6f6444d"));
            add(new Music("Relax In The Forest","relax-in-the-forest.mp3","89cb0c4b-0e57-4b46-920c-b5a7ffb14d68"));
        }
    };

    public MutableLiveData<ArrayList<Music>> getMusic(){
        final MutableLiveData<ArrayList<Music>> result = new MutableLiveData<>();
        result.setValue(musicList);
        return result;
    }

}
