package br.ufjf.dcc192;

import java.util.ArrayList;
import org.repodriller.domain.Commit;
import org.repodriller.domain.Modification;
import org.repodriller.persistence.PersistenceMechanism;
import org.repodriller.scm.CommitVisitor;
import org.repodriller.scm.SCMRepository;

public class DevelopersVisitors implements CommitVisitor {
    
	public void process(SCMRepository repo, Commit commit, PersistenceMechanism writer) {
               Commitss co = new Commitss(commit.getHash(), commit.getCommitter().getName(), commit.getCommitter().getEmail(), commit.getMsg(), commit.getModifications());
               Inicial.commits.add(co);
        }        
}