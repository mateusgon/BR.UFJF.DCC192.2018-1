package br.ufjf.dcc192;

import java.util.ArrayList;
import org.repodriller.domain.Commit;
import org.repodriller.domain.Modification;
import org.repodriller.persistence.PersistenceMechanism;
import org.repodriller.scm.CommitVisitor;
import org.repodriller.scm.SCMRepository;

public class DevelopersVisitors implements CommitVisitor {
    
	public void process(SCMRepository repo, Commit commit, PersistenceMechanism writer) {
               Commits co = new Commits(commit.getHash(), commit.getAuthor().getName(), commit.getAuthor().getEmail(), commit.getMsg(), commit.getModifications());
               Inicial.commits.add(co);
        }        
}