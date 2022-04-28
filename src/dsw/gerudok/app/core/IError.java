package dsw.gerudok.app.core;

import dsw.gerudok.app.errorObserver.ErrorPublisher;

public interface IError extends ErrorPublisher {
    void handleError(String string);
}
