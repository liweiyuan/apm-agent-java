/*-
 * #%L
 * Elastic APM Java agent
 * %%
 * Copyright (C) 2018 Elastic and contributors
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package co.elastic.apm.api;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * This class is the main entry point of the public API for the Elastic APM agent.
 * <p>
 * The tracer gives you access to the currently active transaction and span.
 * It can also be used to track an exception.
 * To use the API, you can just invoke the static methods on this class.
 * </p>
 * Use this API to set a custom transaction name,
 * for example:
 * <pre>{@code
 * ElasticApm.currentTransaction().setName("SuchController#muchMethod");
 * }</pre>
 */
public class ElasticApm {

    private ElasticApm() {
        // do not instantiate
    }

    /**
     * Use this method to create a custom transaction.
     * <p>
     * Note that the agent will do this for you automatically when ever your application receives an incoming HTTP request.
     * You only need to use this method to create custom transactions.
     * </p>
     * <p>
     * It is important to call {@link Transaction#end()} when the transaction has ended.
     * A best practice is to use the transaction in a try-catch-finally block.
     * Example:
     * </p>
     * <pre>
     * Transaction transaction = tracer.startTransaction()
     * try {
     *     transaction.setName("MyController#myAction");
     *     span.setType(Transaction.TYPE_REQUEST);
     *     // do your thing...
     * } catch (Exception e) {
     *     ElasticApm.captureException(e);
     *     throw e;
     * } finally {
     *     transaction.end();
     * }
     * </pre>
     *
     * @return the started transaction
     */
    @Nonnull
    public static Transaction startTransaction() {
        Object transaction = doStartTransaction();
        return transaction != null ? new TransactionImpl(transaction) : NoopTransaction.INSTANCE;
    }

    private static Object doStartTransaction() {
        // co.elastic.apm.api.ElasticApmInstrumentation.StartTransactionInstrumentation.doStartTransaction
        return null;
    }

    /**
     * Returns the currently running transaction.
     * <p>
     * If there is no current transaction, this method will return a noop transaction,
     * which means that you never have to check for {@code null} values.
     * </p>
     *
     * @return The currently running transaction, or a noop transaction (never {@code null}).
     */
    @Nonnull
    public static Transaction currentTransaction() {
        Object transaction = doGetCurrentTransaction();
        return transaction != null ? new TransactionImpl(transaction) : NoopTransaction.INSTANCE;
    }

    private static Object doGetCurrentTransaction() {
        // co.elastic.apm.api.ElasticApmInstrumentation.CurrentTransactionInstrumentation.doGetCurrentTransaction
        return null;
    }

    /**
     * Returns the currently running span.
     * <p>
     * If there is no current span, this method will return a noop span,
     * which means that you never have to check for {@code null} values.
     * </p>
     *
     * @return The currently running span, or a noop span (never {@code null}).
     */
    @Nonnull
    public static Span currentSpan() {
        Object span = doGetCurrentSpan();
        return span != null ? new SpanImpl(span) : NoopSpan.INSTANCE;
    }

    private static Object doGetCurrentSpan() {
        // co.elastic.apm.api.ElasticApmInstrumentation.CurrentSpanInstrumentation.doGetCurrentSpan
        return null;
    }

    /**
     * Start and return a new custom span associated with the currently active transaction.
     * <p>
     * It is important to call {@link Span#end()} when the span has ended.
     * A best practice is to use the span in a try-catch-finally block.
     * Example:
     * </p>
     * <pre>
     * Span span = tracer.startSpan()
     * try {
     *     span.setName("SELECT FROM customer");
     *     span.setType("db.mysql.query");
     *     // do your thing...
     * } catch (Exception e) {
     *     ElasticApm.captureException(e);
     *     throw e;
     * } finally {
     *     span.end();
     * }
     * </pre>
     *
     * @return the started span, or {@code null} if there is no current transaction
     */
    @Nonnull
    public static Span startSpan() {
        Object span = doStartSpan();
        return span != null ? new SpanImpl(span) : NoopSpan.INSTANCE;
    }

    private static Object doStartSpan() {
        // co.elastic.apm.api.ElasticApmInstrumentation.StartSpanInstrumentation.doStartSpan
        return null;
    }

    /**
     * Captures an exception and reports it to the APM server.
     *
     * @param e the exception to record
     */
    public static void captureException(@Nullable Exception e) {
        // co.elastic.apm.api.ElasticApmInstrumentation.CaptureExceptionInstrumentation.captureException
    }

}
