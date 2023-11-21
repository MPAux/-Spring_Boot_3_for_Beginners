package com.luv2code.aopdemo.dao;

import org.springframework.stereotype.Repository;

@Repository
public class MembershipDAOImpl implements MembershipDAO{
    @Override
    public void addAccount() {
        System.out.println(getClass() + ": DO MY DB WORK, ADDING A MEMBERSHIP ACCOUNT");
    }

    @Override
    public void addSillyMember() {
        System.out.println(getClass() + ": DO MY DB WORK, ADDING A SILLY MEMBERSHIP ACCOUNT");
    }

    @Override
    public boolean addBooleanMember() {
        System.out.println(getClass() + ": DO MY DB WORK, ADDING A BOOLEAN MEMBERSHIP ACCOUNT");
        return false;
    }

    @Override
    public void goToSleep() {
        System.out.println(getClass() + ": goes to sleep");
    }


}
