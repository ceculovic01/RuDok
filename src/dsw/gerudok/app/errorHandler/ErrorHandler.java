package dsw.gerudok.app.errorHandler;

import dsw.gerudok.app.errorObserver.ErrorSubscriber;
import dsw.gerudok.app.core.IError;

public class ErrorHandler implements IError {

    private ErrorSubscriber errorSubscriber;

    public ErrorHandler() {
    }

    @Override
    public void handleError(String string) {
        if(string.equals("emptyName")){
            notified("Morate uneti naziv!");
        }
        if(string.equals("sameNameNode")){
            notified("Naziv već postoji!");
        }
        if(string.equals("removeWorkspace")){
            notified("Nije dozvoljeno brisanje radnog prostora!");
        }
        if(string.equals("removeSelectedNodeNull")){
            notified("Morate selektovati šta želite da obrišete!");
        }
        if(string.equals("addToSlot")){
            notified("Ne možete dodati na slot!");
        }
        if(string.equals("DocumentNotSelected")){
            notified("Morate selektovati dokument!");
        }
        if(string.equals("CantBeShared")){
            notified("Ne postoji odgovarajući projekat!");
        }
        if(string.equals("*InName")){
            notified("Uneli ste nedozvoljeni simbol za naziv!");
        }
        if(string.equals("objectNotSelected")){
            notified("Morate selektovati objekat!");
        }
        if(string.equals("addToPage")){
            notified("Dodavanje na stranicu se vrši preko palete!");
        }
        if(string.equals("removeSlot")){
            notified("Brisanje slota je moguće samo preko palete!");
        }
        if(string.equals("SaveProjectNotSelected")){
            notified("Morate selektovati projekat koji želite da sačuvate!");
        }
        if(string.equals("BadOpenedFile")){
            notified("Izabrali ste neodgovarajući fajl!");
        }
        if(string.equals("SameNameProject")){
            notified("Projekat sa istim imenom već postoji!");
        }
    }

    @Override
    public void notified(String notification) {
        errorSubscriber.update(notification);
    }

    @Override
    public void addSubscriber(ErrorSubscriber errorSubscriber) {
        this.errorSubscriber = errorSubscriber;
    }

    @Override
    public void removeSubscriber(ErrorSubscriber errorSubscriber) {

    }
}
