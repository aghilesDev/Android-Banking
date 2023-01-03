package com.example.cnep.cnepe_banking.DomainLayer.Repository;

//import com.android.internal.util.Predicate;
import com.example.cnep.cnepe_banking.Models.EntityBase;

import java.util.Set;
import java.util.function.Predicate;

/**
 * Created by Aghiles on 2017-03-23.
 */

public interface IBaseRepository<T extends EntityBase> {
    Set<T> get(Predicate<T> predicate);
    Set<T> getAll();
    void update(Predicate<T> predicate,T val);
}
