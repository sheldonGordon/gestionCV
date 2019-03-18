package appli.gestionCV.dao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnitUtil;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;



public final class DaoTools {
	
	public enum SortOrder {
		ASC, DESC
	};

	/**
	 * ajoute une nouvelle entity au contexte JPA (persist).
	 *
	 * @param t
	 *            instance d'une entité à ajouter.
	 */
	public static <T> T create(final EntityManager em, final T t) {
		em.persist(t);
		return t;
	}

	/**
	 * met à jour les données portées par l'instance de T (merge).
	 *
	 * @param t
	 */
	public static <T> T update(final EntityManager em, final T t) {
		final T managedInstance = em.merge(t);
		return managedInstance;
	}

	/**
	 * supprime l'instance de T.
	 *
	 * @param t
	 */
	public static <T> void delete(final EntityManager em, Class<T> type, final T t) {
		final PersistenceUnitUtil puu = em.getEntityManagerFactory().getPersistenceUnitUtil();
		final T attachedEntity = em.getReference(type, puu.getIdentifier(t));
		em.remove(attachedEntity);
	}

	/**
	 * récupère toutes les instances de l'entité T.
	 *
	 * @return liste des instances, éventuellement triées.
	 */
	public static <T> List<T> readAll(final EntityManager em, Class<T> type) {
		return readAll(em, type, (String[]) null);
	}

	/**
	 * récupère les instances de l'entité T, éventuellement triées par ordre
	 * Ascendant (ASC) sur une liste d'attributs.
	 *
	 * @return liste des instances, éventuellement triées.
	 */
	public static <T> List<T> readAll(final EntityManager em, Class<T> type, final String... orderBy) {
		return readAll(em, type, SortOrder.ASC, orderBy);
	}


	//TODO mettre un offset
	
	public static <T> List<T> readAll(final EntityManager em, Class<T> type,  final SortOrder sortOrder, final String... orderBy) {
		final CriteriaBuilder qb = em.getCriteriaBuilder();
		final CriteriaQuery<T> c = qb.createQuery(type);
		final Root<T> from = c.from(type);
		addSort(qb, c, from, sortOrder, orderBy);
		final TypedQuery<T> query = em.createQuery(c);
		return query.getResultList();
	}

	/**
	 * lit une instance de T dont l'ID est passé en paramètre (find).
	 *
	 * @param id
	 * @return instance d'une entité
	 */
	public static <T> T read(final EntityManager em,  Class<T> type, final Object id) {
		return em.find(type, id);
	}

	/**
	 * effectue une recherche simple sur la valeur d'un attribut avec l'opérateur
	 * d'égalité. Le résultat est trié (ou non) selon une liste d'attributs par
	 * ordre "ASC".
	 *
	 * @param parameterName
	 *            attribut testé.
	 * @param parameterValue
	 *            valeur à tester.
	 * @param orderBy
	 *            liste d'attributs pour le tri ascendant.
	 * @return liste triée des entités correpondant à la recherche.
	 */
	public static <T> List<T> search(final EntityManager em, Class<T> type, final String parameterName, final Object parameterValue, final String... orderBy) {
		return search(em, type, parameterName, parameterValue, SortOrder.ASC, orderBy);
	}

	/**
	 * 
	 * @param em
	 * 	{@link EntityManager}
	 * @param type
	 * 
	 * @param parameterName
	 * @param parameterValue
	 * @param sortOrder
	 * @param orderBy
	 * @return
	 */
	public static <T> List<T> search(final EntityManager em, Class<T> type, final String parameterName, final Object parameterValue, final SortOrder sortOrder, final String... orderBy) {
		final CriteriaBuilder qb = em.getCriteriaBuilder();
		final CriteriaQuery<T> c = qb.createQuery(type);
		final Root<T> from = c.from(type);
		final Predicate restriction = qb.equal(from.get(parameterName), parameterValue);
		c.where(restriction);
		addSort(qb, c, from, sortOrder, orderBy);
		final TypedQuery<T> query = em.createQuery(c);
		return query.getResultList();
	}

	/**
	 * cherche la première occurrence de la classe T où l'attribut passé en
	 * paramètre est égal à l'objet passé en paramètre.
	 *
	 * @param parameterName
	 *            attribut à tester
	 * @param parameterValue
	 *            valeur à trouver
	 * @return instance de T, ou alors retourne null si aucune occurence n'a été
	 *         trouvée..
	 */
	public static <T> T searchFirstResult(final EntityManager em, Class<T> type, final String parameterName, final Object parameterValue){
		final CriteriaBuilder qb = em.getCriteriaBuilder();
		final CriteriaQuery<T> c = qb.createQuery(type);
		final Root<T> from = c.from(type);
		final Predicate restriction = qb.equal(from.get(parameterName), parameterValue);
		c.where(restriction);
		final TypedQuery<T> query = em.createQuery(c);
		List<T> results = query.getResultList();
		if(!results.isEmpty()) {
			return results.get(0);
		}else {
			return null;
		}
	}

	/**
	 * retourne le nombre d'occurences (tuples) en base de données de la classe T.
	 *
	 * @return nombre d'occurences.
	 */
	public static <T> Long count(final EntityManager em, final Class<T> cl) {
		final CriteriaBuilder qb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> c = qb.createQuery(Long.class);
		c.select(qb.count(c.from(cl)));
		return em.createQuery(c).getSingleResult();
	}

	/**
	 * permet de savoir si la table associée à cette classe ne contient aucun tuple
	 * en base de données.
	 *
	 * @return true si la table est vide, false dans le cas contraire.
	 */
	public static <T> boolean isEmpty(final EntityManager em, final Class<T> cl) {
		return count(em, cl) == 0;
	}

	/**
	 * retourne "true" si une entité contient un attribut dont la valeur est
	 * spécifiée.
	 *
	 * @param parameterName
	 *            attribut à tester
	 * @param parameterValue
	 *            valeur à tester
	 * @return vrai si une entité a été trouvé, false dans le cas contraire.
	 */
	public static <T> boolean exists(final EntityManager em, final String parameterName, final Object parameterValue, final Class<T> cl) {
		final CriteriaBuilder qb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> c = qb.createQuery(Long.class);
		c.select(qb.count(c.from(cl)));
		final Root<T> from = c.from(cl);
		final Predicate restriction = qb.equal(from.get(parameterName), parameterValue);
		c.where(restriction);
		final Long found = em.createQuery(c).getSingleResult();
		return found != 0;
	}

	/**
	 * Rafraichit l'instance en mémoire par rapport à la base de données.
	 *
	 * @param t
	 *            instance à relire depuis la base de données.
	 *
	 */
	@SuppressWarnings("unchecked")
	public static <T> void refresh(final EntityManager em, final T t) {
		final PersistenceUnitUtil puu = em.getEntityManagerFactory().getPersistenceUnitUtil();
		final T managedInstance = (T) em.getReference(t.getClass(), puu.getIdentifier(t));
		
		em.refresh(managedInstance);
	}


	/**
	 * Méthode privée qui ajouter un critère de tri à une requête Criteria. Cette
	 * méthode est utilisée en interne par readAll() et search().
	 *
	 * @param criteriaBuilder
	 * @param query
	 * @param from
	 * @param orderBy
	 */
	private static <T> void addSort(final CriteriaBuilder criteriaBuilder, final CriteriaQuery<T> query, final Root<T> from, final SortOrder sortOrder, final String... orderBy) {
		if ((orderBy != null) && (orderBy.length > 0)) {
			final List<Order> orders = new ArrayList<>();
			for (final String orderParameter : orderBy) {
				Order order = null;
				if (sortOrder.equals(SortOrder.DESC)) {
					order = criteriaBuilder.desc(from.get(orderParameter));
				} else {
					order = criteriaBuilder.asc(from.get(orderParameter));
				}
				orders.add(order);
			}
			query.orderBy(orders);
		}
	}

	/**
	 * Creation si inexistance en base de l'objet en fonction des champs passé en
	 * parametre
	 *
	 * @author MCH MAURY
	 * 
	 * @param em
	 * 			{@link EntityManager} Entity Manager
	 * @param type
	 * 			{@link Class} Type
	 * 
	 * @param obj
	 *            <T> objet à inserer
	 * @param paramName
	 *            nom des attributs sur lesquels tester l'existence
	 */
	public static <T> void createIfNotExist(final EntityManager em, final Class<T> type, final T obj, final String... paramName) {
		// on cree la requte
		final CriteriaBuilder qb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> c = qb.createQuery(Long.class);
		c.select(qb.count(c.from(type)));
		final Root<T> from = c.from(type);
		// on recupere la liste des field de l'objet concerné par la recherche
		final Set<Field> listeField = getAllFields(obj.getClass());
		final List<Predicate> listePredicate = new ArrayList<>();
		for (final String p : paramName) {
			for (final Field fi : listeField) {
				if (fi.getName().equals(p)) {
					fi.setAccessible(true);
					try {
						final Predicate restriction = qb.equal(from.get(fi.getName()), fi.get(obj));
						listePredicate.add(restriction);
					}
					catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
					fi.setAccessible(false);
					break;
				}
			}
		}
		c.where(listePredicate.toArray(new Predicate[] {}));
		final Long found = em.createQuery(c).getSingleResult();
		
		//TODO FAIRE UN TRY CATCH
		
		if (found == 0) {
			create(em, obj);
		} else {
			System.err.println("/ ! \\ L'objet existe déjà et n'a pas été enregistré !");
		}
	}

	/**
	 * retourne un set des champs présents dans la classe, même hérités, quelle
	 * que soit leur visibilité.
	 *
	 * @author MCH MAURY
	 *
	 * @param classe
	 *            {@link Class} classe à introspecter.
	 *            
	 * @return 
	 * 			{@link Set} des fileds de la classe instrospecté.
	 */
	private static <T> Set<Field> getAllFields(final Class<T> type) {
		final Set<Field> fields = new LinkedHashSet<>();
		fields.addAll(Arrays.asList(type.getDeclaredFields()));
		if (type.getSuperclass() != null) {
			// récursivité tant qu'on a pas atteind la classe mère "Object".
			fields.addAll( getAllFields(type.getSuperclass()));
		}
		return fields;
	}

	/**
	 * Trouve l'ensemble des résultats à partir d'une NQ
	 *
	 * @author MCH MAURY
	 * @param em
	 * 			{@link EntityManager} Entity Manager
	 * @param type
	 * 			{@link Class} Type
	 * @param nameNQ
	 *          {@link String} Nom de la NamedQuery
	 * @param args
	 *          {@link Map} Map des arguments nécessaires à la requête.
	 * @param limite
	 * 			nombre de résultats à retourner
	 * 
	 * @return {@link List} d'objet de classe <T>
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> findAllByNamedQuery(final EntityManager em, final Class<T> type, final String nameNQ, final Map<String, ? extends Object> args, final int limite) {
		return (List<T>) createQueryFromNamedQuery(em, type, nameNQ, args).setMaxResults(limite).getResultList();
	}

	/**
	 * Trouve l'ensemble des résultats à partir d'une NQ
	 *
	 * @author MCH MAURY
	 * @param em
	 * 			{@link EntityManager} Entity Manager
	 * @param type
	 * 			{@link Class} Type
	 * @param nameNQ
	 *          {@link String} Nom de la NamedQuery
	 * @param args
	 *          {@link Map} Map des arguments nécessaires à la requête.
	 *
	 * @return {@link List} d'objet de classe <T>
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> findAllByNamedQuery(final EntityManager em, final Class<T> type, final String nameNQ, final Map<String, ? extends Object> args) {
		return (List<T>) createQueryFromNamedQuery(em, type, nameNQ, args).getResultList();
	}


	/**
	 * Trouve l'ensemble des 10 premier résultats à partir d'une NQ
	 *
	 * @author MCH MAURY
	 * 
	 * @param em
	 * 			{@link EntityManager} Entity Manager
	 * @param type
	 * 			{@link Class} Type
	 * @param nameNQ
	 *          {@link String} Nom de la NamedQuery
	 *
	 * @return {@link List} d'objet de classe <T>
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> findAllByNamedQuery(final EntityManager em, final Class<T> type, final String nameNQ) {
		return (List<T>) createQueryFromNamedQuery(em, type, nameNQ, null).setMaxResults(10).getResultList();
	}

	/**
	 * Trouve l'ensemble des résultats à partir d'une NQ
	 *
	 * @author MCH MAURY
	 * 
	 * @param em
	 * 			{@link EntityManager} Entity Manager
	 * @param type
	 * 			{@link Class} Type
	 * @param nameNQ
	 *          {@link String} Nom de la NamedQuery
	 * @param limite
	 * 			nombre de résultats à retourner
	 *
	 * @return {@link List} d'objet de classe <T>
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> findAllByNamedQuery(final EntityManager em, final Class<T> type, final String nameNQ, final int limite) {
		return (List<T>) createQueryFromNamedQuery(em, type, nameNQ, null).setMaxResults(limite).getResultList();
	}
	
	/**
	 * Trouve un résultat à partir d'une NQ
	 *
	 * @author MCH MAURY
	 * 
	 * @param em
	 * 			{@link EntityManager} Entity Manager
	 * @param type
	 * 			{@link Class} Type
	 * @param nameNQ
	 *          {@link String} Nom de la NamedQuery
	 * @param args
	 *          {@link Map}  Map des arguments nécessaires à la requête.
	 *
	 * @return  {@link Object} de classe <T>
	 */
	@SuppressWarnings("unchecked")
	public static <T> T findByNamedQuery(final EntityManager em, final Class<T> type, final String nameNQ, final Map<String, ? extends Object> args) {
		return (T) createQueryFromNamedQuery(em, type, nameNQ, args).getSingleResult();
	}

	/**
	 * Trouve l'ensemble des résultats d'execution d'une NQ sans paramêtres
	 *
	 * @author MCH MAURY
	 * 
	 * @param em
	 * 			{@link EntityManager} Entity Manager
	 * @param nameNQ
	 * 			{@link String}  Nom de la NQ
	 * 
	 * @return
	 * 			{@link List} résultant de la requete
	 */
	public static List<? extends Object> findAllObjectByNamedQuery(final EntityManager em, final String nameNQ) {
		return createQueryFromNamedQuery(em, Object.class, nameNQ, null).getResultList();
	}
	
	/**
	 * Trouve l'ensemble des résultats d'execution d'une NQ
	 *
	 * @author MCH MAURY
	 * 
	 * @param em
	 * 			{@link EntityManager} Entity Manager
	 * @param nameNQ
	 * 			{@link String} Nom de la NQ
	 * @param args
	 * 			{@link Map} Map des arguments nécéssaires à la requête.
	 * 
	 * @return
	 * 			{@link List} résultant de la requete
	 */
	public static List<? extends Object> findAllObjectByNamedQuery(final EntityManager em, final String nameNQ, final Map<String, ? extends Object> args) {
		return createQueryFromNamedQuery(em, Object.class, nameNQ, args).getResultList();
	}
	
	/**
	 * Trouve le résultat d'execution d'une NQ
	 * 	 
	 * @author MCH MAURY
	 * 
	 * @param em
	 * 			{@link EntityManager} Entity Manager
	 * @param nameNQ
	 * 			{@link String} Nom de la NQ
	 * @param args
	 * 			{@link Map} Map des arguments nécéssaires à la requête.
	 * 
	 * @return
	 * 			{@link Object} résultant de la requete
	 */
	public static <T> Object findObjectByNamedQuery(final EntityManager em, final String nameNQ, final Map<String, ? extends Object> args) {
		return createQueryFromNamedQuery(em, Object.class, nameNQ, args).getSingleResult();
	}

	/**
	 * Execution d'une NamedQuery
	 *
	 * @author MCH MAURY
	 * 
	 * @param em
	 * 			{@link EntityManager} Entity Manager
	 * @param type
	 * 			{@link Class} Type
	 * @param nameNQ
	 *          {@link String} Nom de la NamedQuery
	 * @param args
	 *          {@link Map} Map des arguments nécessaires à la requête.
	 *
	 * @return {@link TypedQuery} La query à executer
	 */
	private static <T> TypedQuery<? extends Object> createQueryFromNamedQuery(final EntityManager em, final Class<T> type, final String nameNQ, final Map<String, ? extends Object> args) {
		final TypedQuery<? extends Object> query = createTypedQuery(em, type, nameNQ);
		if (args != null) {
			for (final Entry<String, ? extends Object> entry : args.entrySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		return query;
	}

	/**
	 * Creation d'une NamedQuery
	 * @param em
	 * 			{@link EntityManager} Entity Manager
	 * @param type
	 * 			{@link Class} Type
	 * @param nameNQ
	 *			{@link String} nom de la NQ
	 *
	 * @return {@link TypedQuery} : query à executer
	 */
	private static <T> TypedQuery<? extends Object> createTypedQuery(final EntityManager em, final Class<T> type, final String nameNQ) {
		return em.createNamedQuery(nameNQ, type);
	}
}
