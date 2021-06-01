package com.example.checklstapp.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.checklstapp.db.AppDatabase;
import com.example.checklstapp.db.Category;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private MutableLiveData<List<Category>> listOfCategory;
    private AppDatabase appDatabase;
    public MainActivityViewModel(Application application){
        super(application);
        listOfCategory = new MutableLiveData<>();
        appDatabase = AppDatabase.getDBinstance(getApplication().getApplicationContext());
    }

    public MutableLiveData<List<Category>> getCategoryListObserver(){
        return listOfCategory;
    }

    public void getAllCategoryList(){
        List<Category> categoriesList = appDatabase.checkingListDao().getAllCategoriesList();
        if(categoriesList.size() > 0){
            listOfCategory.postValue(categoriesList);
        }else{
            listOfCategory.postValue(null);
        }
    }

    public void insertCategory(String catName){
        Category category = new Category();
        category.categoryName = catName;
        appDatabase.checkingListDao().insertCategory(category);
        getAllCategoryList();
    }

    public void updateCategory(Category category){
        appDatabase.checkingListDao().updateCategory(category);
        getAllCategoryList();
    }

    public void deleteCategory(Category category){
        appDatabase.checkingListDao().deleteCategory(category);
        getAllCategoryList();
    }


}
