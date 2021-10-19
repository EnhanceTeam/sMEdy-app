package com.example.smedy.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.smedy.model.Meditation;

import java.util.ArrayList;

public class MeditationRepository {
    private static MeditationRepository repository;

    public MeditationRepository(){

    }

    public static MeditationRepository getInstance(){
        if(repository == null){
            repository = new MeditationRepository();
        }

        return repository;
    }

    public MutableLiveData<ArrayList<Meditation>> getMeditation(){
        final MutableLiveData<ArrayList<Meditation>> result = new MutableLiveData<>();
        ArrayList<Meditation> listMeditasi = new ArrayList<>();

        listMeditasi.add(new Meditation("Tidur Lebih Nyenyak", "meditasi ini dapat membuat tidurmu jauh lebih nyenyak", "10 menit", "https://firebasestorage.googleapis.com/v0/b/smedy-enhance.appspot.com/o/meditasi%2FJam%20Tidur%20Berantakan%20Karena%20Insomnia%20Lakukan%20Meditasi%2010%20Menit%20Ini%20Sebelum%20Tidur!.mp4?alt=media&token=bedae422-0399-4451-907c-4aad046ca171"));
        listMeditasi.add(new Meditation("Obati Dirimu!", "meditasi ini dapat menyembuhkan penyakit-penyakit batin yang sedang kamu alami", "12 menit", "https://firebasestorage.googleapis.com/v0/b/smedy-enhance.appspot.com/o/meditasi%2FLakukan%20Meditasi%20Ini%20Untuk%20Penyembuhan%20Diri!%20Rasakan%20Manfaat%20Meditasi%20Ini%20Untuk%20Tubuhmu!.mp4?alt=media&token=f4fccfac-151b-4005-b1f9-a1b6f3ba707e"));
        listMeditasi.add(new Meditation("Tenangkan Tekanan dalam Hidupmu", "meditasi ini dapat memberikan ketenangan bagi kamu yang sedang mengalami tekanan hidup", "11 menit", "https://firebasestorage.googleapis.com/v0/b/smedy-enhance.appspot.com/o/meditasi%2FMerasa%20Hidupmu%20Dalam%20Kondisi%20Tertekan%20Lakukan%20Meditasi%20Ini%20Untuk%20Membuat%20Hidupmu%20Lebih%20Tenang!.mp4?alt=media&token=11c99356-d79f-4f59-83ba-2b38b8df265a"));
        listMeditasi.add(new Meditation("Meditasi Pikiran", "Hentikan overthinking mu dengan melakukan meditasi ini!", "8 menit", "https://firebasestorage.googleapis.com/v0/b/smedy-enhance.appspot.com/o/meditasi%2FSering%20Merasa%20Overthingking%20Lakukan%20Meditasi%20Mindfulness%20Ini%20Untuk%20Mengatasinya!.mp4?alt=media&token=a6ebda06-179c-4bd7-87b3-26bdf99171d1"));
        listMeditasi.add(new Meditation("Perbaiki Fokus", "Dapatkan kembali fokusmu dari tekanan-tekanan dalam pikiran dengan melakukan meditasi ini!", "10 menit", "https://firebasestorage.googleapis.com/v0/b/smedy-enhance.appspot.com/o/meditasi%2FSulit%20Fokus%20karena%20Sering%20Tertekan%20Lakukan%20Meditasi%2010%20Menit%20ini%20Untuk%20Mengatasinya!.mp4?alt=media&token=148a4224-d61f-43f3-a223-5d855a8d13b2"));

        result.setValue(listMeditasi);

        return result;
    }


}
