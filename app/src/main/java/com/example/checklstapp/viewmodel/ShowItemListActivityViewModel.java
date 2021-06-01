package com.example.checklstapp.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.checklstapp.db.AppDatabase;
import com.example.checklstapp.db.Category;
import com.example.checklstapp.db.Items;

import java.util.List;

public class ShowItemListActivityViewModel extends AndroidViewModel {

    private MutableLiveData<List<Items>> listOfItems;
    private AppDatabase appDatabase;
    public ShowItemListActivityViewModel(Application application){
        super(application);
        listOfItems = new MutableLiveData<>();
        appDatabase = AppDatabase.getDBinstance(getApplication().getApplicationContext());
    }

    public MutableLiveData<List<Items>> getItemsListObserver(){
        return listOfItems;
    }

    public void getAllItemsList(int categoryID){
        List<Items> itemsList = appDatabase.checkingListDao().getAllItemList(categoryID);
        if(itemsList.size() > 0){
            listOfItems.postValue(itemsList);
        }else{
            listOfItems.postValue(null);
        }
    }

    public void insertItems(Items item){
        appDatabase.checkingListDao().insertItems(item);
        getAllItemsList(item.categoryId);
    }

    public void updateItems(Items item){
        appDatabase.checkingListDao().updateItems(item);
        getAllItemsList(item.categoryId);
    }

    public void deleteItems(Items item){
        appDatabase.checkingListDao().deleteItems(item);
        getAllItemsList(item.categoryId);
    }


}
