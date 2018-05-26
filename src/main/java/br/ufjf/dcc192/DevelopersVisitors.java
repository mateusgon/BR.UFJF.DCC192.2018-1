package br.ufjf.dcc192;

import org.repodriller.domain.Commit;
import org.repodriller.persistence.PersistenceMechanism;
import org.repodriller.scm.CommitVisitor;
import org.repodriller.scm.SCMRepository;

public class DevelopersVisitors implements CommitVisitor {

	public void process(SCMRepository repo, Commit commit, PersistenceMechanism writer) {
		writer.write(
			commit.getHash(),
			commit.getCommitter().getName(),
                        commit.getCommitter().getEmail(),
			commit.getMsg()
		);
                Commitss c = new Commitss(commit.getHash(), commit.getCommitter().getName(), commit.getCommitter().getEmail(), commit.getMsg());
	}
}