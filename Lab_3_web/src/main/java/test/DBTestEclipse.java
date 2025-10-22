package test;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import models.Point;
import org.eclipse.persistence.jpa.JpaEntityManager;
import org.eclipse.persistence.expressions.Expression;
import org.eclipse.persistence.expressions.ExpressionBuilder;
import org.eclipse.persistence.queries.ReadAllQuery;
import org.eclipse.persistence.sessions.Session;

import java.util.List;

public class DBTestEclipse {
    @PersistenceContext(unitName = "appPU")
    private EntityManager em;
    public void request(String sid, String from, String to, Integer limit) {
        JpaEntityManager jem = em.unwrap(JpaEntityManager.class);
        Session session = jem.getActiveSession();

        ReadAllQuery query = new ReadAllQuery(Point.class);
        ExpressionBuilder b = new ExpressionBuilder();

        Expression criteria = b.get("sessionId").equal(b.getParameter("sid"))
                .and(b.get("releaseTime").between(b.getParameter("from"), b.getParameter("to")))
                .and(b.get("hit").equal(true));

        query.setSelectionCriteria(criteria);
        query.addOrdering(b.get("releaseTime").descending());
        query.addArgument("sid", String.class);
        query.addArgument("from", java.time.LocalDateTime.class);
        query.addArgument("to",   java.time.LocalDateTime.class);

        @SuppressWarnings("unchecked")
        List<Point> points = (List<Point>) session.executeQuery(
                query,
                List.of(new Object[]{sid, from, to})
        );
        query.setMaxRows(limit);
    }
}
