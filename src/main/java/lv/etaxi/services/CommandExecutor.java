package lv.etaxi.services;


import lv.etaxi.commands.DomainCommand;
import lv.etaxi.commands.DomainCommandResult;

public interface CommandExecutor {

    <T extends DomainCommandResult> T execute(DomainCommand<T> domainCommand);

}
