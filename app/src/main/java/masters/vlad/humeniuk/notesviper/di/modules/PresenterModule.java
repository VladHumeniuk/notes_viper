package masters.vlad.humeniuk.notesviper.di.modules;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import masters.vlad.humeniuk.notesviper.di.scopes.UserScope;
import masters.vlad.humeniuk.notesviper.domain.interactors.AddNoteInteractor;
import masters.vlad.humeniuk.notesviper.domain.interactors.CategoriesListInteractor;
import masters.vlad.humeniuk.notesviper.domain.interactors.InitDbInteractor;
import masters.vlad.humeniuk.notesviper.domain.interactors.NotesListInteractor;
import masters.vlad.humeniuk.notesviper.presentation.categories.presenter.CategoriesListPresenter;
import masters.vlad.humeniuk.notesviper.presentation.categories.presenter.CategoriesListPresenterImpl;
import masters.vlad.humeniuk.notesviper.presentation.categories.router.CategoriesListRouter;
import masters.vlad.humeniuk.notesviper.presentation.createnote.presenter.CreateNotePresenter;
import masters.vlad.humeniuk.notesviper.presentation.createnote.presenter.CreateNotePresenterImpl;
import masters.vlad.humeniuk.notesviper.presentation.createnote.router.CreateNoteRouter;
import masters.vlad.humeniuk.notesviper.presentation.editnote.presenter.EditNotePresenter;
import masters.vlad.humeniuk.notesviper.presentation.editnote.presenter.EditNotePresenterImpl;
import masters.vlad.humeniuk.notesviper.presentation.editnote.router.EditNoteRouter;
import masters.vlad.humeniuk.notesviper.presentation.main.presenter.MainPresenter;
import masters.vlad.humeniuk.notesviper.presentation.main.presenter.MainPresenterImpl;
import masters.vlad.humeniuk.notesviper.presentation.main.router.MainRouter;
import masters.vlad.humeniuk.notesviper.presentation.noteslist.presenter.NotesListPresenter;
import masters.vlad.humeniuk.notesviper.presentation.noteslist.presenter.NotesListPresenterImpl;
import masters.vlad.humeniuk.notesviper.presentation.noteslist.router.NotesListRouter;

import static masters.vlad.humeniuk.notesviper.di.modules.SchedulerModule.IO_SCHEDULER;
import static masters.vlad.humeniuk.notesviper.di.modules.SchedulerModule.UI_SCHEDULER;

@Module
public class PresenterModule {

    @Provides
    @UserScope
    MainPresenter provideMainPresenter(MainRouter mainRouter,
                                       @Named(IO_SCHEDULER) Scheduler ioScheduler,
                                       InitDbInteractor initDbInteractor) {
        return new MainPresenterImpl(mainRouter, ioScheduler, initDbInteractor);
    }

    @Provides
    @UserScope
    NotesListPresenter provideNotesListPresenter(NotesListRouter notesListRouter,
                                                 @Named(IO_SCHEDULER) Scheduler ioScheduler,
                                                 @Named(UI_SCHEDULER) Scheduler uiScheduler,
                                                 NotesListInteractor notesListInteractor) {
        return new NotesListPresenterImpl(notesListRouter, ioScheduler, uiScheduler, notesListInteractor);
    }

    @Provides
    @UserScope
    CreateNotePresenter provideCreateNotePresenter(CreateNoteRouter router,
                                                   @Named(IO_SCHEDULER) Scheduler ioScheduler,
                                                   @Named(UI_SCHEDULER) Scheduler uiScheduler,
                                                   AddNoteInteractor addNoteInteractor) {
        return new CreateNotePresenterImpl(router, ioScheduler, uiScheduler, addNoteInteractor);
    }

    @Provides
    @UserScope
    EditNotePresenter provideEditNotePresenter(EditNoteRouter router) {
        return new EditNotePresenterImpl(router);
    }

    @Provides
    @UserScope
    CategoriesListPresenter provideCategoriesListPresenter(@Named(IO_SCHEDULER) Scheduler ioScheduler,
                                                           @Named(UI_SCHEDULER) Scheduler uiScheduler,
                                                           CategoriesListRouter router,
                                                           CategoriesListInteractor categoriesListInteractor) {
        return new CategoriesListPresenterImpl(ioScheduler, uiScheduler, router, categoriesListInteractor);
    }
}
